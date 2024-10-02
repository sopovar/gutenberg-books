package ge.sopovardidze.gutenberg_books.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.sopovardidze.gutenberg_books.presentation.utils.Converters

@Database(
    entities = [BookEntity::class, RemoteKeys::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BookDatabase: RoomDatabase() {

    abstract val dao: BookDao
    abstract val remoteKeysDao: RemoteKeysDao
}