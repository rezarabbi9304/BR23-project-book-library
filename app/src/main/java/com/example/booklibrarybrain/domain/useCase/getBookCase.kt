package com.example.booklibrarybrain.domain.useCase

import com.example.booklibrarybrain.data.dto.BookItem
import com.example.booklibrarybrain.domain.repository.bookRepository
import com.example.booklibrarybrain.util.Resource
import kotlinx.coroutines.flow.Flow

class getBookCase (
    val respository:bookRepository
){


    suspend fun invoke():Flow<Resource<BookItem>>{
      return  respository.getBooks()
    }
}