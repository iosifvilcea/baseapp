package com.blankthings.baseapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.Network.*
import platform.darwin.dispatch_get_main_queue

actual class NetworkMonitorFactory {
    actual fun provideNetworkMonitor(): NetworkMonitor = IosNetworkMonitor()
}

class IosNetworkMonitor : NetworkMonitor {
    private val pathMonitor = nw_path_monitor_create()
    private val _isOnline = MutableStateFlow(false)

    override val isOnline: Flow<Boolean> = _isOnline.asStateFlow()

    init {
        startMonitoring()
    }

    private fun startMonitoring() {
        nw_path_monitor_set_update_handler(pathMonitor) { path ->
            val isConnected = nw_path_get_status(path) == nw_path_status_satisfied
            _isOnline.value = isConnected
        }

        nw_path_monitor_set_queue(pathMonitor, dispatch_get_main_queue())
        nw_path_monitor_start(pathMonitor)
    }

    fun stopMonitoring() {
        nw_path_monitor_cancel(pathMonitor)
    }
}