package com.vault.calculator.ui.route.maincalculator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vault.calculator.manager.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainCalculatorViewModel @Inject constructor(
    private val settingsManager: SettingsManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val pin: String? = savedStateHandle[KEY_PIN]

    private var mutableStateFlow =
        MutableStateFlow(
            UiState(
                mode = if (pin == "null" || pin == null) {
                    Mode.SETUP
                } else {
                    Mode.LOGIN
                },
                pin = pin
            )
        )
    val stateFlow = mutableStateFlow.asStateFlow()

    fun onSetPin(pin: String) {
        val firstTimePin = mutableStateFlow.value.firstTimeEnteredPin

        if (firstTimePin == null) {
            mutableStateFlow.update { it.copy(firstTimeEnteredPin = pin) }
        } else {
            if (firstTimePin == pin) {
                viewModelScope.launch {
                    settingsManager.updateLoginPin(pin)
                    // show message
                    mutableStateFlow.update {
                        it.copy(
                            pin = pin,
                            mode = Mode.LOGIN,
                            firstTimeEnteredPin = null,
                            showMessage = "Success!"
                        )
                    }
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        showMessage = "Use same pin as before!"
                    )
                }
            }
        }
    }

    fun onLoginTry(pin: String) {
        if (pin == mutableStateFlow.value.pin) {
            mutableStateFlow.update { it.copy(navigateToPrivateScreen = true) }
        } else {
            // show calculation
        }
    }

    fun handleOnMessageShown() {
        mutableStateFlow.update { it.copy(showMessage = null) }
    }

    data class UiState(
        val pin: String?,
        val mode: Mode,
        val firstTimeEnteredPin: String? = null,
        val showMessage: String? = null,
        val navigateToPrivateScreen: Boolean = false
    )

    enum class Mode {
        SETUP,
        LOGIN
    }
}