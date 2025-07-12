package com.blankthings.baseapp.utils

import kotlinx.coroutines.flow.Flow

class NetworkMonitorImpl(networkMonitorFactory: NetworkMonitorFactory) : NetworkMonitor {
    override val isOnline: Flow<Boolean> = networkMonitorFactory.provideNetworkMonitor().isOnline
}