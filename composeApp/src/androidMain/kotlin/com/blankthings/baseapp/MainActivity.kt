package com.blankthings.baseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.blankthings.baseapp.database.DatabaseFactory
import com.blankthings.baseapp.datastore.DataStoreFactory
import com.blankthings.baseapp.datastore.DataStoreManagerImpl
import com.blankthings.baseapp.utils.AppContext
import com.blankthings.baseapp.utils.NetworkMonitorFactory
import com.blankthings.baseapp.utils.NetworkMonitorImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContext.init(applicationContext)
        AppDependencies.initializeNetworkMonitor(NetworkMonitorImpl(NetworkMonitorFactory()))
        AppDependencies.initializeDataStore(DataStoreManagerImpl(DataStoreFactory()))
        AppDependencies.initializeDatabase(DatabaseFactory(applicationContext).createRoomDatabase())
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}