package ge.sopovardidze.gutenberg_books.data.repository

import ge.sopovardidze.gutenberg_books.data.local.BookDao
import ge.sopovardidze.gutenberg_books.data.mapper.toBook
import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val bookDao: BookDao
): BookRepository {

    override suspend fun getBookById(bookId: Int): Flow<Book> {
        return bookDao.getBookById(bookId).map { it.toBook() }
    }
}
