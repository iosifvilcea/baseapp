package com.blankthings.baseapp.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class NetworkMonitorFactory {
    actual fun provideNetworkMonitor(): NetworkMonitor {
        val context = AppContext.getContext() ?: throw IllegalStateException("NetworkMonitor: Context is not initialized")
        return AndroidNetworkMonitor(context = context)
    }
}

class AndroidNetworkMonitor(context: Context) : NetworkMonitor {
    private var connectivityManager: ConnectivityManager? =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    override val isOnline: Flow<Boolean>
        get() = callbackFlow {
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                .build()

            val callback = object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    connectivityManager?.getNetworkCapabilities(network)?.let {
                        val hasInternet = it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        trySend(hasInternet)
                    }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    trySend(false)
                }

                override fun onLost(network: Network) {
                    trySend(false)
                }

                override fun onUnavailable() {
                    trySend(false)
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val hasInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    trySend(hasInternet)
                }
            }

            connectivityManager?.registerNetworkCallback(networkRequest, callback)

            awaitClose {
                connectivityManager?.unregisterNetworkCallback(callback)
            }
        }
}