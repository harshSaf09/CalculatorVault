package com.vault.calculator.ui

import com.vault.calculator.ui.route.maincalculator.KEY_PIN


sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object GettingStarted : Screen("getting_started")
    object MainCalculator : Screen("main_calculator")
    object MainVault : Screen("main_vault")
    object RecoverySetup : Screen("recoverySetup")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}