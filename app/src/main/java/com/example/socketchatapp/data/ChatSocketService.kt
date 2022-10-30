package com.example.socketchatapp.data

import com.example.socketchatapp.domain.model.Message
import com.example.socketchatapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {
    suspend fun initSession(
        username: String
    ): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessage(): Flow<Message>

    suspend fun closeSession()

    companion object{
        const val BASE_URL = "ws://ktor-chat-server-mito.herokuapp.com"
    }

    sealed class Endpoints(val url: String){
        object ChatSocket: Endpoints("$BASE_URL/chat-socket")
    }
}