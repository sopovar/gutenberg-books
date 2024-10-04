package ge.sopovardidze.gutenberg_books.presentation.utils

sealed class RepoResult<out T> {
    data class Success<T>(val data: T) : RepoResult<T>()
    data class Error<T>(val th: Throwable): RepoResult<T>()
}