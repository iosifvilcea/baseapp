package com.blankthings.baseapp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import platform.Foundation.NSURL
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.WeakReference

actual object AppContext

@OptIn(ExperimentalForeignApi::class)
actual class DataStoreFactory {
    actual fun createDataStore(): DataStore<Preferences> =
        createDataStore(producePath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }
    )
}