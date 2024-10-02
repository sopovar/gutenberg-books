package ge.sopovardidze.gutenberg_books.domain.model

data class Author(
    val birthYear: Int? = 0,
    val deathYear: Int? = 0,
    val name: String? = ""
)
