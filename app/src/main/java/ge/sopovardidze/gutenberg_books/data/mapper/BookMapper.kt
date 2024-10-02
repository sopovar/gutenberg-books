package ge.sopovardidze.gutenberg_books.data.mapper

import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.data.model.AuthorDto
import ge.sopovardidze.gutenberg_books.data.model.BookDto
import ge.sopovardidze.gutenberg_books.domain.model.Author
import ge.sopovardidze.gutenberg_books.domain.model.Book

fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title ?: "",
        authors = authors.map { it.toAuthor() },
        subjects = subjects ,
        bookshelves = bookshelves,
        downloadedCount = download_count,
        image = formats.jpeg
    )
}

fun BookEntity.toBook() : Book {
    return Book(
        id = id,
        title = title,
        authors = authors,
        subjects = subjects,
        bookshelves = bookshelves,
        downloadedCount = downloadedCount,
        image = image
    )
}

fun AuthorDto.toAuthor(): Author {
    return Author(
        birthYear = birth_year,
        deathYear = death_year,
        name = name
    )
}