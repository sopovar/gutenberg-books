package ge.sopovardidze.gutenberg_books.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int = 0,
    val id: Int,
    val title: String? = "",
)
