package com.blankthings.baseapp

import com.blankthings.baseapp.data.KtorClient
import com.blankthings.baseapp.datastore.DataStoreManager
import com.blankthings.baseapp.utils.NetworkMonitor
import com.blankthings.baseapp.utils.NetworkMonitorImpl

// TODO - Use DI instead.
object AppDependencies {
    private val httpClient = KtorClient.create()
    private var dataStoreManager: DataStoreManager? = null
    private var networkMonitor: NetworkMonitor? = null

    fun initializeDataStore(dataStoreManager: DataStoreManager) {
        this.dataStoreManager = dataStoreManager
    }

    fun getDataStoreManager(): DataStoreManager {
        return dataStoreManager ?: throw IllegalStateException("DataStoreManager not initialized")
    }

    fun initializeNetworkMonitor(networkMonitorImpl: NetworkMonitorImpl) {
        networkMonitor = networkMonitorImpl
    }

    fun getNetworkMonitor(): NetworkMonitor {
        return networkMonitor ?: throw IllegalStateException("NetworkMonitor not initialized")
    }

    fun getHttpClient() = httpClient
}