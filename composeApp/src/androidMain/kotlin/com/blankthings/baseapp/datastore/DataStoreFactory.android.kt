package com.blankthings.baseapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.lang.ref.WeakReference

actual object AppContext {
    private var context: WeakReference<Context?>? = null
    fun init(context: Context) {
        this.context = WeakReference(context)
    }

    fun getContext(): Context? = context?.get()
}

actual class DataStoreFactory {
    actual fun createDataStore(): DataStore<Preferences> = createDataStoreAndroid()
}

fun createDataStoreAndroid(): DataStore<Preferences> = createDataStore(
    producePath = {
        val context = AppContext.getContext()
        context?.filesDir?.resolve(dataStoreFileName)?.absolutePath
            ?: throw IllegalStateException("Context is not initialized")
    }
)