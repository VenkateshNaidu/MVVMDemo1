package com.venkygithub.mvvmdemo1.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.venkygithub.mvvmdemo1.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionIntercepter (context: Context ): Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailabe())
            throw NoInternetException("Internet is not connected")
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailabe() : Boolean {

        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it !=null && it.isConnected
        }
    }

}