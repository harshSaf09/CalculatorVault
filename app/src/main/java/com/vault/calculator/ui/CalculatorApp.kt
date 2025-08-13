package com.vault.calculator.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vault.calculator.ui.route.gettingstarted.GettingStartedRoute
import com.vault.calculator.ui.route.maincalculator.KEY_PIN
import com.vault.calculator.ui.route.maincalculator.MainCalculatorRoute
import com.vault.calculator.ui.route.privatescreen.PrivateScreenRoute
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
            route = Screen.MainCalculator.route + "/{$KEY_PIN}",
            arguments = listOf(
                navArgument(name = KEY_PIN) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            MainCalculatorRoute(navController)
        }
        composable(route = Screen.MainVault.route) {
            PrivateScreenRoute(navController)
        }
        composable(route = Screen.RecoverySetup.route) {
            Text("Don't know")
        }
        composable(route = Screen.GettingStarted.route) {
            GettingStartedRoute(navController)
        }
    }
}