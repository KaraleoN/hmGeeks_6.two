package com.geeks.hmgeeks_62.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

// WelcomeScreen
@Composable
fun WelcomeScreen(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Onboarding Flow!")
        Button(onClick = onNext) {
            Text(text = "Next")
        }
    }
}

// RegistrationScreen
@Composable
fun RegistrationScreen(onRegister: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    // Track if the button should be enabled based on input
    LaunchedEffect(username) {
        isButtonEnabled = username.isNotBlank()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registration Screen")
        BasicTextField(
            value = username,
            onValueChange = { username = it },
            decorationBox = { innerTextField ->
                if (username.isEmpty()) {
                    Text("Enter your name", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Button(
            onClick = { onRegister(username) },
            enabled = isButtonEnabled
        ) {
            Text(text = "Register")
        }
    }
}

// ConfirmationScreen
@Composable
fun ConfirmationScreen(username: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Confirmation Screen")
        if (!username.isNullOrEmpty()) {
            Text(text = "Welcome, $username!")
        } else {
            Text(text = "No username provided!")
        }
    }
}