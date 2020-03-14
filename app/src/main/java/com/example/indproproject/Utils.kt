package com.example.indproproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * this utils class contains all the utility classes, constants of this project
 */

object Constants {

    const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    const val TITLE = "title"
    const val ROWS = "rows"
    const val DESCRIPTION = "description"
    const val IMAGE_HREF = "imageHref"

    const val TAG_NETWORK_CALLS_MANAGER = "Network Calls Manager"
}

/**
 * This method is used for to check is network is connected or not
 */
fun isInternetAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            //It will check for both wifi and cellular network
            return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            )
        }
        return false
    } else {
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}