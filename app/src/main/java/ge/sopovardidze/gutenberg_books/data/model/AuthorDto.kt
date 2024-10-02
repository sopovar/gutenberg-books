package ge.sopovardidze.gutenberg_books.data.model

data class AuthorDto(
    val birth_year: Int? = 0,
    val death_year: Int? = 0,
    val name: String? = ""
)