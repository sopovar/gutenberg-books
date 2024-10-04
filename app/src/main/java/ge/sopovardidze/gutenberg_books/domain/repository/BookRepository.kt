package ge.sopovardidze.gutenberg_books.domain.repository

import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.presentation.utils.RepoResult
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getBookById(bookId: Int): Flow<RepoResult<Book>>
}
