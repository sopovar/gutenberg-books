package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.gutenberg_books.domain.usecases.GetBookByIsUseCase
import ge.sopovardidze.gutenberg_books.presentation.utils.RepoResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookByIsUseCase: GetBookByIsUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<BookDetailUiModel> = MutableStateFlow(BookDetailUiModel())
    val state = _state.asStateFlow()

    fun getBookById(id: Int) {
        viewModelScope.launch {
            getBookByIsUseCase.invoke(id).onEach { result ->
                when(result) {
                    is RepoResult.Success -> {
                        _state.value = _state.value.copy(
                            book = result.data,
                            errorMsg = null
                        )
                    }
                    is RepoResult.Error -> {
                        _state.value = _state.value.copy(
                            book = null,
                            errorMsg = result.th.message
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
