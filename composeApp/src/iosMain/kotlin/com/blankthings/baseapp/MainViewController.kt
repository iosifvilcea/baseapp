package com.blankthings.baseapp

import androidx.compose.ui.window.ComposeUIViewController
import com.blankthings.baseapp.datastore.DataStoreFactory
import com.blankthings.baseapp.datastore.DataStoreManagerImpl
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    AppDependencies.initializeDataStore(DataStoreManagerImpl(DataStoreFactory()))
    return ComposeUIViewController {
        App()
    }
}