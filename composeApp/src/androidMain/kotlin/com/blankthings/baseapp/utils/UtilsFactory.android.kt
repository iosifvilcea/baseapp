package com.blankthings.baseapp.utils

import android.content.Context
import java.lang.ref.WeakReference

actual object AppContext {
    private var context: WeakReference<Context?>? = null

    fun init(context: Context) {
        this.context = WeakReference(context)
    }

    fun getContext(): Context? = context?.get()
}