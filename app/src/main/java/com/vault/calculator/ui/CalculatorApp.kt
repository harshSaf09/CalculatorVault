package com.vault.calculator.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vault.calculator.ui.route.gettingstarted.GettingStartedRoute
import com.vault.calculator.ui.route.maincalculator.KEY_SHOW_ENTER_PASSWORD
import com.vault.calculator.ui.route.maincalculator.MainCalculatorRoute
import com.vault.calculator.ui.route.splash.SplashScreenRoute

@Composable
fun CalculatorApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreenRoute(navController)
        }
        composable(
            route = Screen.MainCalculator.route + "/{$KEY_SHOW_ENTER_PASSWORD}",
            arguments = listOf(
                navArgument(name = KEY_SHOW_ENTER_PASSWORD) {
                    type = NavType.BoolType
                    defaultValue = false
                    nullable = true
                }
            )
        ) { entry ->
            MainCalculatorRoute(
                navController,
                showEnterPassword = entry.arguments?.getBoolean(KEY_SHOW_ENTER_PASSWORD) ?: false
            )
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