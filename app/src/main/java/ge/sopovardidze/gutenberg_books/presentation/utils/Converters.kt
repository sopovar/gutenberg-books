package ge.sopovardidze.gutenberg_books.presentation.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ge.sopovardidze.gutenberg_books.domain.model.Author

class Converters {

    @TypeConverter
    fun fromAuthorsList(authors: List<Author?>?): String {
        return Gson().toJson(authors)
    }

    @TypeConverter
    fun toAuthorsList(authorsString: String): List<Author?>? {
        val listType = object : TypeToken<List<Author?>>() {}.type
        return Gson().fromJson(authorsString, listType)
    }

    @TypeConverter
    fun fromSubjectsList(subjects: List<String>?): String {
        return Gson().toJson(subjects)
    }

    @TypeConverter
    fun toSubjectsList(subjectsString: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(subjectsString, listType)
    }
}
