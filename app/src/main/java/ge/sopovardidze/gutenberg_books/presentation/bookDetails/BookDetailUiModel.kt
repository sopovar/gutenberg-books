package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import ge.sopovardidze.gutenberg_books.domain.model.Book

data class BookDetailUiModel(
    val book: Book? = null,
    val errorMsg: String? = null,
)
