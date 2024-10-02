package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.gutenberg_books.domain.usecases.GetBookByIsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookByIsUseCase: GetBookByIsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    fun getBookById(id: Int) {
        viewModelScope.launch {
            val book = getBookByIsUseCase.invoke(id)
            book.collect {
                Log.e("123123", "BookById = ${it.title}")
            }
        }
    }
}
