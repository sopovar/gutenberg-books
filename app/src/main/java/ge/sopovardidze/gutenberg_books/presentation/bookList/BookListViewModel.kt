package ge.sopovardidze.gutenberg_books.presentation.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.data.remote.BookApi
import ge.sopovardidze.gutenberg_books.data.remote.BooksPagingSource
import ge.sopovardidze.gutenberg_books.presentation.utils.NETWORK_PAGE_SIZE
import ge.sopovardidze.gutenberg_books.presentation.utils.PRE_FETCH_DISTANCE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookApi: BookApi
): ViewModel() {

    private lateinit var bookPagingSource: BooksPagingSource

    val getPagingBooks: Flow<PagingData<BookEntity>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                bookPagingSource = BooksPagingSource(bookApi)
                bookPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun getPagingConfig() = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false,
        prefetchDistance = PRE_FETCH_DISTANCE
    )
}
