
package com.example.socketchatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.socketchatapp.theme.SocketChatAppTheme
import com.example.socketchatapp.ui.chat.ChatScreen
import com.example.socketchatapp.ui.username.UsernameScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocketChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "username_screen"
    ) {
        composable("username_screen") {
            UsernameScreen(onNavigate = navController::navigate)
        }
        composable(
            route = "chat_screen/{username}",
            arguments = listOf(
                navArgument(name = "username") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val username = it.arguments?.getString("username")
            ChatScreen(username = username)
        }
    }
}