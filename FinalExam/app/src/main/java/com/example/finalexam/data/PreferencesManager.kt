package com.example.finalexam.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("final_exam_prefs")

class PreferencesManager(private val context: Context) {

    companion object {
        val KEY_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val KEY_USERNAME = stringPreferencesKey("username")
        val KEY_LAST_JOKE = stringPreferencesKey("last_joke")
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_LOGGED_IN] ?: false
    }

    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LOGGED_IN] = value
        }
    }

    val username: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_USERNAME]
    }

    suspend fun setUsername(name: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USERNAME] = name
        }
    }

    val lastJoke: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_LAST_JOKE]
    }

    suspend fun setLastJoke(joke: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LAST_JOKE] = joke
        }
    }
}
