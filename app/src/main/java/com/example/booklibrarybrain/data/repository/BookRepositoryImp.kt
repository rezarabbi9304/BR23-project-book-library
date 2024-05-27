package com.example.booklibrarybrain.data.repository

import com.example.booklibrarybrain.data.BookAPi
import com.example.booklibrarybrain.data.dto.BookItem
import com.example.booklibrarybrain.data.dto.DataDto
import com.example.booklibrarybrain.domain.repository.bookRepository
import com.example.booklibrarybrain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class BookRepositoryImp @Inject  constructor(
    private val api:BookAPi
):bookRepository{
    private val books = listOf(

        DataDto ( id= 101,
            name= "Programming Language C",
            image= "https://picsum.photos/id/1/300/300",
            url="https://en.wikipedia.org/wiki/C_(programming_language)"),
        DataDto ( id= 102,
            name= "The Lord of the Rings",
            image= "https://picsum.photos/id/2/300/300",
            url="https://en.wikipedia.org/wiki/The_Lord_of_the_Rings")
        ,       DataDto ( id= 103,
            name= "Pride and Prejudice",
            image= "https://picsum.photos/id/3/300/300",
            url="https://en.wikipedia.org/wiki/Pride_and_Prejudice")
        ,       DataDto ( id= 104,
            name= "The Hitchhiker's Guide to the Galaxy",
            image= "https://picsum.photos/id/4/300/300",
            url="https://en.wikipedia.org/wiki/The_Hitchhiker%27s_Guide_to_the_Galaxy")
        ,       DataDto ( id= 105,
            name= "The Great Gatsby",
            image= "https://picsum.photos/id/5/300/300",
            url="https://en.wikipedia.org/wiki/The_Great_Gatsby") ,
        DataDto ( id= 105,
            name= "One Hundred Years of Solitude",
            image= "https://picsum.photos/id/6/300/300",
            url="https://en.wikipedia.org/wiki/One_Hundred_Years_of_Solitude")
    )

    override suspend fun getBooks(): Flow<Resource<BookItem>> = flow{
            emit(Resource.Loading(
                data = BookItem(
                    data =books.map { it.dataDtoTO() },
                    message = "Success",
                    status = 200

                )
            ))

        try {
            val book = api.getBooks()
            emit(Resource.Success(
                data = book

            ))
        }catch (Ex:HttpException){
            emit(Resource.Error(
                message = "Cant Load The Data from network.This is mock data",
                data = BookItem(
                    data =books.map { it.dataDtoTO() },
                    message = "Success",
                    status = 200

                )
            ))
        }catch (Ex:IOException){
            emit(Resource.Error(
                message = "Cant connect to network",
                data = BookItem(
                    data =books.map { it.dataDtoTO() },
                    message = "Success",
                    status = 200

                )
            ))
        }

    }
}