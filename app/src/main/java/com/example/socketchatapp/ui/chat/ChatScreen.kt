package com.example.socketchatapp.ui.chat

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChatScreen(
    username: String?,
    viewModel: ChatViewModel = hiltViewModel()
) {

    Text(text = username ?: "noop")
    
}