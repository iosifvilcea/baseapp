package com.blankthings.baseapp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface DataStoreManager {
    val dataStore: DataStore<Preferences>
}