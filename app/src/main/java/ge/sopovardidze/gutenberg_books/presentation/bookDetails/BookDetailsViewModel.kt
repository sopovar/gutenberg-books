package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.domain.usecases.GetBookByIsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookByIsUseCase: GetBookByIsUseCase,
) : ViewModel() {

    private var _bookDetails: MutableStateFlow<Book?> = MutableStateFlow<Book?>(null)
    val bookDetails = _bookDetails.asStateFlow()

    fun getBookById(id: Int) {
        viewModelScope.launch {
            val book = getBookByIsUseCase.invoke(id)
            book.collect {
                _bookDetails.value = it
            }
        }
    }
}
