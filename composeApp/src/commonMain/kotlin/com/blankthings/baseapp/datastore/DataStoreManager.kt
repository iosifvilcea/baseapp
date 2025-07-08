package com.blankthings.baseapp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    val dataStore: DataStore<Preferences>

    suspend fun saveString(key: String, value: String)
    fun getString(key: String): Flow<String?>
}