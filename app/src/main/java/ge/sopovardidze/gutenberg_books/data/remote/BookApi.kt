package ge.sopovardidze.gutenberg_books.data.remote

import ge.sopovardidze.gutenberg_books.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("books/")
    suspend fun getBooks(
        @Query("page") page: Int
    ): BookResponse
}
