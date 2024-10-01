package ge.sopovardidze.gutenberg_books.data.model

data class BookResponse(
    val results: List<BookDto>,
    val count: Int,
    val previous: String?,
    val next: String?
)
