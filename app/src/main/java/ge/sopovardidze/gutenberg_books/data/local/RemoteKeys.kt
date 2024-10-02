package ge.sopovardidze.gutenberg_books.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val bookId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)