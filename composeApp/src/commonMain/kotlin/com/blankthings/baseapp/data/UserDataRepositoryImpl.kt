package com.blankthings.baseapp.data

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.blankthings.baseapp.datastore.DataStoreManager
import com.blankthings.baseapp.model.DarkThemeConfig
import com.blankthings.baseapp.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class UserDataRepositoryImpl(val dataStoreManager: DataStoreManager): UserDataRepository {
    companion object {
        private val DARK_THEME_CONFIG_KEY = stringPreferencesKey("dark_theme_config")
    }

    override val userData: Flow<UserData>
        get() = dataStoreManager.dataStore.data
            .map { preferences ->
                val darkThemeConfig = preferences[DARK_THEME_CONFIG_KEY]
                    ?.let(DarkThemeConfig::valueOf)
                    ?: DarkThemeConfig.FOLLOW_SYSTEM
                UserData(darkThemeConfig = darkThemeConfig)
        }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        dataStoreManager.dataStore.edit { preferences ->
            preferences[DARK_THEME_CONFIG_KEY] = darkThemeConfig.name
        }
    }
}