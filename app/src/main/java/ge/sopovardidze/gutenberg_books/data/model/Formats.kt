package ge.sopovardidze.gutenberg_books.data.model

import com.google.gson.annotations.SerializedName

data class Formats(
    @SerializedName("text/html")
    val html: String? = null,

    @SerializedName("application/epub+zip")
    val epub: String?  = null,

    @SerializedName("application/x-mobipocket-ebook")
    val mobi: String? = null,

    @SerializedName("application/rdf+xml")
    val rdf: String? = null,

    @SerializedName("image/jpeg")
    val jpeg: String? = null,

    @SerializedName("text/plain; charset=us-ascii")
    val plainText: String? = null,

    @SerializedName("application/octet-stream")
    val octetStream: String? = null
)