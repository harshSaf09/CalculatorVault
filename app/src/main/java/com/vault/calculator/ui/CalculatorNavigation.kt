package com.vault.calculator.ui

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object GettingStarted : Screen("getting_started")
    object MainCalculator : Screen("main_calculator")
    object MainVault: Screen("main_vault")
    object RecoverySetup : Screen("recoverySetup")
}