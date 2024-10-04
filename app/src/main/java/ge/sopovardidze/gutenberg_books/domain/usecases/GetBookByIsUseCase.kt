package ge.sopovardidze.gutenberg_books.domain.usecases

import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.domain.repository.BookRepository
import ge.sopovardidze.gutenberg_books.presentation.utils.RepoResult
import kotlinx.coroutines.flow.Flow

class GetBookByIsUseCase(
    private val bookRepository: BookRepository
) {

    operator fun invoke(bookId: Int): Flow<RepoResult<Book>> {
        return bookRepository.getBookById(bookId)
    }
}
