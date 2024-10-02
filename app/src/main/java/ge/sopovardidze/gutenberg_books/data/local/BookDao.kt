package ge.sopovardidze.gutenberg_books.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Upsert
    suspend fun upsetAll(books: List<BookEntity>)

    @Query("SELECT * FROM BookEntity")
    fun pagingSource(): PagingSource<Int, BookEntity>

    @Query("DELETE FROM BookEntity")
    suspend fun clearAll()

    @Query("SELECT * FROM bookentity WHERE id=:id")
    fun getBookById(id: Int): Flow<BookEntity>
}
