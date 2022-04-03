package com.chslcompany.githubrepo.core.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.fragment.app.FragmentActivity

class NetworkChangeReceiver : BroadcastReceiver() {

    private var connectionChangeCallback: ConnectionChangeCallback? = null

    override fun onReceive(context: Context, intent: Intent) {
      isInternetAvailable(context)
    }

    @Suppress("DEPRECATION")
    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                this.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    fun setConnectionChangeCallback(connectionChangeCallback: ConnectionChangeCallback) {
        this.connectionChangeCallback = connectionChangeCallback
    }

    interface ConnectionChangeCallback {
        fun onConnectionChange(isConnected: Boolean)
    }

    companion object {
        private const val CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
        private val intentFilter = IntentFilter(CONNECTIVITY_CHANGE)
        private val networkChangeReceiver = NetworkChangeReceiver()

        fun FragmentActivity.networkChangeReceiver(connectionChangeCallback: ConnectionChangeCallback) {
            this.registerReceiver(networkChangeReceiver, intentFilter)
            networkChangeReceiver.setConnectionChangeCallback(connectionChangeCallback)
        }

        fun FragmentActivity.networkChangeUnregisterReceiver(connectionChangeCallback: ConnectionChangeCallback) {
            this.unregisterReceiver(networkChangeReceiver);
            networkChangeReceiver.setConnectionChangeCallback(connectionChangeCallback)
        }
    }

}