package com.example.socketchatapp.ui.chat

import androidx.lifecycle.SavedStateHandle
import com.example.socketchatapp.data.ChatSocketService
import com.example.socketchatapp.data.MessageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService,
    private val savedStateHandle: SavedStateHandle
) {


}