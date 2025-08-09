package com.vault.calculator.ui.theme.route.maincalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun MainCalculatorRoute(
    navHostController: NavHostController,
    viewModel: MainCalculatorViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Main Calculator screen")
    }
}