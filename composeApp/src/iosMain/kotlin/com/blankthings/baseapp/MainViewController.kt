package com.blankthings.baseapp

import androidx.compose.ui.window.ComposeUIViewController
import com.blankthings.baseapp.database.DatabaseFactory
import com.blankthings.baseapp.datastore.DataStoreFactory
import com.blankthings.baseapp.datastore.DataStoreManagerImpl
import com.blankthings.baseapp.utils.NetworkMonitorFactory
import com.blankthings.baseapp.utils.NetworkMonitorImpl
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    AppDependencies.initializeNetworkMonitor(NetworkMonitorImpl(NetworkMonitorFactory()))
    AppDependencies.initializeDataStore(DataStoreManagerImpl(DataStoreFactory()))
    AppDependencies.initializeDatabase(DatabaseFactory.createRoomDatabase())
    return ComposeUIViewController {
        App()
    }
}