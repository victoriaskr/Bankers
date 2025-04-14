package com.example.bankers

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankers.ui.theme.BankersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankersTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("main2") { MainActivity2() }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold { innerPadding ->
        if (isLandscape) {
            // Горизонтальная ориентация
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Welcome to the Bankers App!",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MainButtons(navController, context)
                }
            }
        } else {
            // Вертикальная ориентация
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Welcome to the Bankers App!",
                    style = MaterialTheme.typography.headlineMedium
                )
                MainButtons(navController, context)
            }
        }
    }
}

@Composable
fun MainButtons(navController: NavController, context: android.content.Context) {
    Button(onClick = { navController.navigate("main2") }) {
        Text(text = "Read about bankers")
    }

    Button(onClick = {
        context.startActivity(Intent(context, EmptyActivity::class.java))
    }) {
        Text(text = "Open EmptyActivity")
    }

    Button(onClick = {
        context.startActivity(Intent(context, MainActivity3::class.java))
    }) {
        Text(text = "Open BasicActivity")
    }

    Button(onClick = {
        context.startActivity(Intent(context, MainActivity6::class.java))
    }) {
        Text(text = "Open Bottom Navigation Activity")
    }

    Button(onClick = {
        context.startActivity(Intent(context, MainActivity5::class.java))
    }) {
        Text(text = "Open Navigation Drawer Activity")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BankersTheme {
        MainScreen(rememberNavController())
    }
}
