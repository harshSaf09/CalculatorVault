package com.vault.calculator.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val KEY_PIN = stringPreferencesKey("login_pin")
private val KEY_NUMBER_OF_TIMES_MEDIA_DENIED = intPreferencesKey("num_of_times_media_denied")

class SettingsManager @Inject constructor(
    private val settingsDataStore: DataStore<Preferences>,
) {
    val loginPin: Flow<String?> = settingsDataStore.data.map { preferences ->
        preferences[KEY_PIN]
    }

    suspend fun updateLoginPin(pin: String) {
        settingsDataStore.edit { settings ->
            settings[KEY_PIN] = pin

        }
    }


    suspend fun clearAll() {
        settingsDataStore.edit { settings ->
            settings.clear()
        }
    }
}
