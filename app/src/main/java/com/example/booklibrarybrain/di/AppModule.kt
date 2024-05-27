package com.example.booklibrarybrain.di

import com.example.booklibrarybrain.data.BookAPi
import com.example.booklibrarybrain.data.repository.BookRepositoryImp
import com.example.booklibrarybrain.domain.repository.bookRepository
import com.example.booklibrarybrain.domain.useCase.getBookCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideBookApi(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://example.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Provides
    @Singleton
    fun provideBooksApi(retrofit: Retrofit):BookAPi{
        return  retrofit.create(BookAPi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(api: BookAPi ): bookRepository {
        return  BookRepositoryImp(api = api)
    }

    @Provides
    @Singleton
    fun provideBookUseCase(repository: bookRepository):getBookCase{
        return  getBookCase(repository)
    }
}