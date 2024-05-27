package com.example.booklibrarybrain.data

import com.example.booklibrarybrain.data.dto.BookItem
import retrofit2.http.GET

interface BookAPi {

    @GET("api/book-list") //mock api
    suspend fun getBooks():BookItem
}