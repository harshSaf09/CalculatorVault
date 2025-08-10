package com.vault.calculator.ui.route.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vault.calculator.ui.Screen

@Composable
fun SplashScreenRoute(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val nextScreen by viewModel.nextScreen.collectAsState()

    LaunchedEffect(nextScreen) {
        nextScreen?.let { destination ->
            navController.navigate(destination.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Loading...")
    }
}