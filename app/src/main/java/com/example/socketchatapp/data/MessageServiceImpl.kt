package com.example.socketchatapp.data

import com.example.socketchatapp.data.dto.MessagesDto
import com.example.socketchatapp.domain.model.Message
import io.ktor.client.*
import io.ktor.client.request.*

class MessageServiceImpl(private val client: HttpClient): MessageService {
    override suspend fun getAllMessages(): List<Message> {
        return try {
            client.get<List<MessagesDto>>(MessageService.Endpoints.GetAllMessages.url).map { it.toMessages() }
        }catch (e:Exception){
            listOf()
        }
    }
}