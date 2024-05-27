package com.example.booklibrarybrain.data.dto

data class BookItemDto(
    val data: List<DataDto>,
    val message: String,
    val status: Int
){
   fun bookItemTo():BookItem{
       return BookItem(
           data = data.map { it.dataDtoTO() },
           message = message,
           status =status
       )
   }


}