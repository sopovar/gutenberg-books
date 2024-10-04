package ge.sopovardidze.gutenberg_books.data.repository

import ge.sopovardidze.gutenberg_books.data.local.BookDao
import ge.sopovardidze.gutenberg_books.data.mapper.toBook
import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.domain.repository.BookRepository
import ge.sopovardidze.gutenberg_books.presentation.utils.RepoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class BookRepositoryImpl(
    private val bookDao: BookDao,
) : BookRepository {

    override fun getBookById(bookId: Int): Flow<RepoResult<Book>> = flow {
        try {
            val bookFromDb = bookDao.getBookById(bookId)
            if (bookFromDb != null) {
                emit(RepoResult.Success(bookFromDb.toBook()))
            } else {
                emit(RepoResult.Error(Throwable("Book with id not found")))
            }
        } catch (io: IOException) {
            emit(RepoResult.Error(io))
        }
    }
}
