package ge.sopovardidze.gutenberg_books.domain.usecases

import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetBookByIsUseCase(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(bookId: Int): Flow<Book> {
        return bookRepository.getBookById(bookId)
    }
}
