package com.example.socketchatapp.ui.chat

import com.example.socketchatapp.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
