package com.example.booklibrarybrain.data.dto

data class DataDto(
    val id: Int,
    val image: String,
    val name: String,
    val url: String
){
    fun dataDtoTO():Data{
        return Data(
            id = id,
            image = image,
            name = name,
            url = url
        )
    }





}