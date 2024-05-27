package com.example.booklibrarybrain.presentation

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.example.booklibrarybrain.data.dto.BookItem

data class BookState(
    val bookItem:BookItem = BookItem(
        data = emptyList(),
        message = "",
        status = 0
    ),
    val message: String? =null
)
