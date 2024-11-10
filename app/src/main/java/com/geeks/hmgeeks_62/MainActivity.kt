package com.geeks.hmgeeks_62

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.geeks.hmgeeks_62.ui.screens.WelcomeScreen
import com.geeks.hmgeeks_62.ui.screens.RegistrationScreen
import com.geeks.hmgeeks_62.ui.screens.ConfirmationScreen
import com.geeks.hmgeeks_62.ui.theme.HmGeeks_62Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HmGeeks_62Theme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "welcomeScreen",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("welcomeScreen") {
                                WelcomeScreen(onNext = {
                                    navController.navigate("registrationScreen")
                                })
                            }
                            composable("registrationScreen") {
                                RegistrationScreen(onRegister = { username ->
                                    navController.navigate("confirmationScreen/$username")
                                })
                            }
                            composable(
                                "confirmationScreen/{username}",
                                arguments = listOf(navArgument("username") { type = NavType.StringType })
                            ) { backStackEntry ->
                                val username = backStackEntry.arguments?.getString("username")
                                ConfirmationScreen(username = username)
                            }
                        }
                    }
                )
            }
        }
    }
}