package ge.sopovardidze.gutenberg_books.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val birthYear: Int? = 0,
    val deathYear: Int? = 0,
    val name: String? = ""
): Parcelable
