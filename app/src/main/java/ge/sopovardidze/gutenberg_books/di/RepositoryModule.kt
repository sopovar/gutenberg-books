package ge.sopovardidze.gutenberg_books.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.sopovardidze.gutenberg_books.data.local.BookDatabase
import ge.sopovardidze.gutenberg_books.data.repository.BookRepositoryImpl
import ge.sopovardidze.gutenberg_books.domain.repository.BookRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(bookDatabase: BookDatabase): BookRepository =
        BookRepositoryImpl(bookDatabase.dao)
}