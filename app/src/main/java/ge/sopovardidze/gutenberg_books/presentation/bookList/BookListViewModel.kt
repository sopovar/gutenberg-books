package ge.sopovardidze.gutenberg_books.presentation.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.data.mapper.toBook
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    pager: Pager<Int, BookEntity>,
) : ViewModel() {

    var pagingDataFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toBook() }
        }
        .cachedIn(viewModelScope)
}
