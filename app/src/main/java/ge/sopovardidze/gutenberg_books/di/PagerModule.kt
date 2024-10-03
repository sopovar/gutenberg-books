package ge.sopovardidze.gutenberg_books.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.sopovardidze.gutenberg_books.data.local.BookDatabase
import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.data.remote.BookApi
import ge.sopovardidze.gutenberg_books.data.remote.BookRemoteMediator
import dagger.Provides
import ge.sopovardidze.gutenberg_books.presentation.utils.NETWORK_PAGE_SIZE
import ge.sopovardidze.gutenberg_books.presentation.utils.PRE_FETCH_DISTANCE
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object PagerModule {

    @Provides
    @Singleton
    fun provideBookPager(bookApi: BookApi, bookDatabase: BookDatabase): Pager<Int, BookEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = PRE_FETCH_DISTANCE,
                initialLoadSize = 1
            ),
            remoteMediator = BookRemoteMediator(
                bookApi, bookDatabase
            ),
            pagingSourceFactory = {
                bookDatabase.dao.pagingSource()
            }
        )
    }
}
