package com.example.booklibrarybrain.domain.repository

import com.example.booklibrarybrain.data.dto.BookItem
import com.example.booklibrarybrain.util.Resource


interface bookRepository {

  suspend  fun getBooks():kotlinx.coroutines.flow.Flow<Resource<BookItem>>
}