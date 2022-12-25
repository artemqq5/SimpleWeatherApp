package andr.rednifrehtaew.weatherfinder.data.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class InternetListenerImp(private val context: Context) : InternetListener {

    private val connectiveManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    override fun changeStateConnection(): Flow<InternetListener.StatusInternet> {
        return callbackFlow {
            val networkListener = object : ConnectivityManager.NetworkCallback() {
                // network is available for use
                override fun onAvailable(network: Network) {
                    launch { send(InternetListener.StatusInternet.Available) }
                }

                // lost network connection
                override fun onLost(network: Network) {
                    launch { send(InternetListener.StatusInternet.Lost) }
                }
            }

            connectiveManager.registerDefaultNetworkCallback(networkListener)

            awaitClose {
                connectiveManager.unregisterNetworkCallback(networkListener)
            }
        }.distinctUntilChanged()

    }

    override fun isInternet(): InternetListener.StatusInternet {

        val network = connectiveManager.activeNetwork ?: return InternetListener.StatusInternet.Lost
        val activeNetwork = connectiveManager.getNetworkCapabilities(network)
            ?: return InternetListener.StatusInternet.Lost

        return if (
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        ) InternetListener.StatusInternet.Available
        else InternetListener.StatusInternet.Lost
    }


}