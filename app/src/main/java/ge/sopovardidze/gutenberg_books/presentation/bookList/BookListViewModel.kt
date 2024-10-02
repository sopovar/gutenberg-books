package ge.sopovardidze.gutenberg_books.presentation.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.gutenberg_books.data.local.BookDatabase
import ge.sopovardidze.gutenberg_books.data.mapper.toBook
import ge.sopovardidze.gutenberg_books.data.remote.BookApi
import ge.sopovardidze.gutenberg_books.data.remote.BookRemoteMediator
import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.presentation.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class BookListViewModel @Inject constructor(
    bookApi: BookApi,
    private val bookDatabase: BookDatabase,
) : ViewModel() {

    var pagingDataFlow: Flow<PagingData<Book>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        remoteMediator = BookRemoteMediator(bookApi, bookDatabase),
        pagingSourceFactory = {
            bookDatabase.dao.pagingSource()
        }
    ).flow
        .map { pagingData ->
            pagingData.map { it.toBook() }
        }
        .cachedIn(viewModelScope)
}
