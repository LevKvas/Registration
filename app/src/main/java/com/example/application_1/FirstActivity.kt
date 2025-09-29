package com.example.application_1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.application_1.ui.theme.Application_1Theme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


class Activity_1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Application_1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenWithToolbar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithToolbar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6750A4),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        MainScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MainScreenContent(modifier: Modifier = Modifier) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Entrance in the app",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = login,  // to remember login field
            onValueChange = {
                login = it
                showError = false
                            },
            label = { Text("Login") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = showError
        )

        OutlinedTextField(
            value = password, // to remember password field
            onValueChange = {
                password = it
                showError = false},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = showError
        )

        if (showError) {
            Text(
                text = "Wrong login or password!",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                 //must check password and login

                if (checkData(password, login)) {
                    val intent = Intent(context, SecondActivity::class.java)
                    context.startActivity(intent)
                } else {
                    showError = true
                }

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6750A4),
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Go!")
        }
    }
}

fun checkData(getPassword: String, getLogin: String): Boolean{
    val correctPass = "LandayTheory"
    val correctLogin = "Lev"

    return (getPassword == correctPass) && (correctLogin == getLogin)
}

