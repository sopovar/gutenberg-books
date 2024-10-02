package ge.sopovardidze.gutenberg_books.domain.repository

import ge.sopovardidze.gutenberg_books.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    suspend fun getBookById(bookId: Int): Flow<Book>
}
