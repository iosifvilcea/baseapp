package com.blankthings.baseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.blankthings.baseapp.datastore.AppContext
import com.blankthings.baseapp.datastore.DataStoreFactory
import com.blankthings.baseapp.datastore.DataStoreManagerImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContext.init(applicationContext)
        AppDependencies.initializeDataStore(DataStoreManagerImpl(DataStoreFactory()))
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