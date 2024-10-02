package ge.sopovardidze.gutenberg_books.data.model

data class BookDto(
    val authors: List<AuthorDto> = listOf(),
    val bookshelves: List<String> = listOf(),
    val copyright: Boolean? = false,
    val download_count: Long? = 0,
    val formats: FormatsDto,
    val id: Int,
    val languages: List<String> = listOf(),
    val media_type: String? = "",
    val subjects: List<String> = listOf(),
    val title: String? = "",
    val translators: List<AuthorDto?>? = listOf()
)