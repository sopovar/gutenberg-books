package ge.sopovardidze.gutenberg_books.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ge.sopovardidze.gutenberg_books.data.local.BookDatabase
import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.data.local.RemoteKeys
import ge.sopovardidze.gutenberg_books.data.mapper.toBookEntity
import ge.sopovardidze.gutenberg_books.presentation.utils.START_PAGE
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BookRemoteMediator(
    private val bookApi: BookApi,
    private val bookDatabase: BookDatabase
): RemoteMediator<Int, BookEntity>() {

    val bookDao = bookDatabase.dao
    val remoteKeysDao = bookDatabase.remoteKeysDao

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: START_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                Log.e("123123", "nextKey = ${nextKey}")
                nextKey
            }
        }

        try {
            val apiResponse = bookApi.getBooks(page)
            val books = apiResponse.results
            val endOfPaginationReached = books.isEmpty()
            bookDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    bookDao.clearAll()
                }
                val prevKey = if (page == START_PAGE) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = books.map {
                    RemoteKeys(bookId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                remoteKeysDao.insertAll(keys)
                bookDao.upsetAll(books.map { it.toBookEntity() })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, BookEntity>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { book ->
                remoteKeysDao.remoteKeysBookId(book.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, BookEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { book ->
                remoteKeysDao.remoteKeysBookId(book.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, BookEntity>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { bookId ->
                remoteKeysDao.remoteKeysBookId(bookId)
            }
        }
    }
}
