package com.example.socketchatapp.data

import com.example.socketchatapp.domain.model.Message

interface MessageService {
    suspend fun getAllMessages(): List<Message>
    companion object{
        const val BASE_URL = "https://ktor-chat-server-mito.herokuapp.com"
    }
    sealed class Endpoints(val url: String){
        object GetAllMessages: Endpoints("$BASE_URL/messages")
    }
}