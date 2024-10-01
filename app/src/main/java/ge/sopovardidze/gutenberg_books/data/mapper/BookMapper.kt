package ge.sopovardidze.gutenberg_books.data.mapper

import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.data.model.BookDto
import ge.sopovardidze.gutenberg_books.domain.model.Book

fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title
    )
}

fun BookEntity.toBook() : Book {
    return Book(
        id = id,
        title = title
    )
}