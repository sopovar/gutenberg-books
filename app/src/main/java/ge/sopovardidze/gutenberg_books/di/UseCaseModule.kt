package ge.sopovardidze.gutenberg_books.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.sopovardidze.gutenberg_books.domain.repository.BookRepository
import ge.sopovardidze.gutenberg_books.domain.usecases.GetBookByIsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetBookByIdUseCase(bookRepository: BookRepository): GetBookByIsUseCase =
        GetBookByIsUseCase(bookRepository)
}
