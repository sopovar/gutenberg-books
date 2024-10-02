package ge.sopovardidze.gutenberg_books.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val id: Int,
    val title: String? = "",
    val authors: List<Author?> = emptyList(),
    val subjects: List<String> = emptyList(),
    val bookshelves: List<String> = emptyList(),
    val image: String?,
    val downloadedCount: Long?
): Parcelable
