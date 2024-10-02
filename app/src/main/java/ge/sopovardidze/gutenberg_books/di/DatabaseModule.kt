package ge.sopovardidze.gutenberg_books.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.sopovardidze.gutenberg_books.data.local.BookDatabase
import ge.sopovardidze.gutenberg_books.presentation.utils.BOOKS_DB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBookDatabase(application: Application): BookDatabase {
        return Room
            .databaseBuilder(application, BookDatabase::class.java, BOOKS_DB)
            .fallbackToDestructiveMigration()
            .build()
    }
}
