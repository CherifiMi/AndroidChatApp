package com.example.socketchatapp.ui.chat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ChatScreen(
    username: String?,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.toastEvent.collectLatest {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.connectToChat()
            } else if (event == Lifecycle.Event.ON_STOP) {
                viewModel.disconnect()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            items(state.messages) { message ->
                val isOwnMessage = message.username == username

                Box(
                    contentAlignment = if (isOwnMessage) Alignment.CenterEnd else Alignment.CenterStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .background(
                                color = if (isOwnMessage) Color.Blue else Color.DarkGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = message.username,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = message.text,
                            color = Color.White
                        )
                        Text(
                            text = message.formattedTime,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(32.dp))
            }

        }
        Row(Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.weight(1f),
                value = viewModel.messageText.value,
                onValueChange = viewModel::onMessageChange,
                placeholder = {
                    Text(text = "Enter txt")
                }
            )
            IconButton(onClick = viewModel::sendMessage) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}