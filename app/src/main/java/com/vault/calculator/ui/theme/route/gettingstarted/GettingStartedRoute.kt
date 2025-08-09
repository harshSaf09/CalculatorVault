package com.vault.calculator.ui.theme.route.gettingstarted

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun GettingStartedRoute(
    navHostController: NavHostController,
    viewModel: GettingStartedViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Getting started screen")
    }
}