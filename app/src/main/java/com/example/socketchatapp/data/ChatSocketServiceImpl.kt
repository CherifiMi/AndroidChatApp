package com.example.socketchatapp.data

import com.example.socketchatapp.domain.model.Message
import com.example.socketchatapp.util.Resource
import kotlinx.coroutines.flow.Flow

class ChatSocketServiceImpl: ChatSocketService {
    override suspend fun initSession(username: String): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun observeMessage(): Flow<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun closeSession() {
        TODO("Not yet implemented")
    }
}