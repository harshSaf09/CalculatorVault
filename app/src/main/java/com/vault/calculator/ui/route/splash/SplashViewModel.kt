package com.vault.calculator.ui.route.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vault.calculator.manager.SettingsManager
import com.vault.calculator.ui.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val settingsManager: SettingsManager) :
    ViewModel() {
    private val _nextScreen = MutableStateFlow<String?>(null)
    val nextScreen: StateFlow<String?> = _nextScreen

    init {
        viewModelScope.launch {
            val pin = settingsManager.loginPin.first()
            if (pin.isNullOrBlank()) {
                _nextScreen.value = Screen.GettingStarted.route
            } else {
                _nextScreen.value = Screen.MainCalculator.withArgs(pin)
            }
        }
    }
}