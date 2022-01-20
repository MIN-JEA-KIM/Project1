package com.cooltechworks.sample

import android.app.Application
import android.util.Log
import com.cooltechworks.sample.ApplicationController
import com.cooltechworks.sample.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by KJH on 2017-06-06.
 */
class ApplicationController : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    var networkService: NetworkService? = null
        private set
    private var baseUrl: String? = null
    fun buildNetworkService(ip: String?, port: Int) {
        synchronized(ApplicationController::class.java) {
            if (networkService == null) {
                baseUrl = String.format("http://%s:%d/", ip, port)
                Log.i(TAG, baseUrl)
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                networkService = retrofit.create(NetworkService::class.java)
            }
        }
    }

    fun buildNetworkService(ip: String?) {
        synchronized(ApplicationController::class.java) {
            if (networkService == null) {
                baseUrl = String.format("http://%s/", ip)
                Log.i(TAG, baseUrl)
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                networkService = retrofit.create(NetworkService::class.java)
            }
        }
    }

    companion object {
        const val TAG = "KJH"
        var instance: ApplicationController? = null
            private set
    }
}