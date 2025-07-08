package com.blankthings.baseapp

import com.blankthings.baseapp.data.KtorClient
import com.blankthings.baseapp.datastore.DataStoreManager

// TODO - Use DI instead.
object AppDependencies {
    private val httpClient = KtorClient.create()
    private var dataStoreManager: DataStoreManager? = null

    fun initializeDataStore(dataStoreManager: DataStoreManager) {
        this.dataStoreManager = dataStoreManager
    }

    fun getDataStoreManager(): DataStoreManager {
        return dataStoreManager ?: throw IllegalStateException("DataStoreManager not initialized")
    }

    fun getHttpClient() = httpClient
}