package com.blankthings.baseapp.utils

expect class NetworkMonitorFactory() {
    fun provideNetworkMonitor(): NetworkMonitor
}