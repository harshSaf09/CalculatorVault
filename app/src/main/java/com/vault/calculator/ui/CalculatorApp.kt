package com.vault.calculator.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vault.calculator.ui.theme.route.gettingstarted.GettingStartedRoute
import com.vault.calculator.ui.theme.route.maincalculator.MainCalculatorRoute
import com.vault.calculator.ui.theme.route.splash.SplashScreenRoute

@Composable
fun CalculatorApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreenRoute(navController)
        }
        composable(route = Screen.MainCalculator.route) {
            MainCalculatorRoute(navController)
        }
        composable(route = Screen.MainVault.route) {
            Text("Don't know")
        }
        composable(route = Screen.RecoverySetup.route) {
            Text("Don't know")
        }
        composable(route = Screen.GettingStarted.route) {
            GettingStartedRoute(navController)
        }
    }
}