package com.example.socketchatapp.data.dto

import com.example.socketchatapp.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*

@Serializable
data class MessagesDto(
    val text: String,
    val timestamp: Long,
    val username: String,
    val id: String,
) {
    fun toMessages(): Message{
        val date = Date(timestamp)
        val formattedDate = DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(date)
        return Message(
            text = text,
            formattedTime = formattedDate,
            username = username
        )
    }
}