package ge.sopovardidze.gutenberg_books.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BookEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class BookDatabase: RoomDatabase() {

    abstract val dao: BookDao
    abstract val remoteKeysDao: RemoteKeysDao
}