package ge.sopovardidze.gutenberg_books.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ge.sopovardidze.gutenberg_books.domain.model.Author

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int = 0,
    val id: Int,
    val title: String,
    val authors: List<Author?>,
    val subjects: List<String> = emptyList(),
    val bookshelves: List<String> = emptyList(),
    val image: String?,
    val downloadedCount: Long?
)
