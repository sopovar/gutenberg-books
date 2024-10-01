package ge.sopovardidze.gutenberg_books.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.data.mapper.toBookEntity
import retrofit2.HttpException

class BooksPagingSource(
    private val bookApi: BookApi
): PagingSource<Int, BookEntity>() {

    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookEntity> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: START_PAGE
            val response = bookApi.getBooks(nextPageNumber)
            val bookEntity = response.results.map { it.toBookEntity() }
            LoadResult.Page(
                data = bookEntity,
                prevKey = if (nextPageNumber == START_PAGE) null else nextPageNumber - 1,
                nextKey = if (bookEntity.isEmpty()) null else nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            if (e is HttpException) {
                val errorString =
                    e.response()?.errorBody()?.byteStream()?.bufferedReader().use { it?.readText() }
                LoadResult.Error(Throwable(errorString))
            } else {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BookEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}