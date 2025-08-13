package com.vault.calculator.ui.route.privatescreen

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
class PrivateViewModel @Inject constructor() :
    ViewModel() {
}