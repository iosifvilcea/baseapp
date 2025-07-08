package com.blankthings.baseapp

import com.blankthings.baseapp.datastore.DataStoreManager

// TODO - Use DI instead.
object AppDependencies {
    private var dataStoreManager: DataStoreManager? = null

    fun getDataStoreManager(): DataStoreManager {
        return dataStoreManager ?: throw IllegalStateException("DataStoreManager not initialized")
    }

    fun initializeDataStore(dataStoreManager: DataStoreManager) {
        this.dataStoreManager = dataStoreManager
    }
}