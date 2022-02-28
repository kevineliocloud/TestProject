package com.test.testapplication.utils

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.test.testapplication.data.ErrorResponse
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object NetworkUtils {

    private var isNetworkConnected = true

    interface OnConnectedListener {
        fun onConnected()
    }

    fun getThrowableError(t: Throwable): ErrorResponse? {
        ConnectivityTask().execute()

        if (t is HttpException) {
            val body = t.response().errorBody()
            val gson = Gson()
            val adapter: TypeAdapter<ErrorResponse> = gson.getAdapter(ErrorResponse::class.java)

            try {
                return adapter.fromJson(body!!.string())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            AppLogger.d("throwable ${t.message}")
            val errorResponse = ErrorResponse()
            errorResponse.message = t.message
            return errorResponse
        }
        return null
    }

    class ConnectivityTask {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        fun execute() {
            executor.execute {
                /*
                * its like doInBackground()
                * */
                handler.post {
                    /*
                    * You can perform any operation that
                    * */

                    try {
                        val urlConnection: HttpURLConnection =
                            URL("https://clients3.google.com/generate_204").openConnection() as HttpURLConnection
                        urlConnection.setRequestProperty("User-Agent", "Android")
                        urlConnection.setRequestProperty("Connection", "close")
                        urlConnection.connectTimeout = 1500
                        urlConnection.connect()
                        urlConnection.responseCode == 204 && urlConnection.contentLength == 0
                        isNetworkConnected = true
                    } catch (e: Exception) {
                        e.printStackTrace()
                        isNetworkConnected = false
                    }
                }
            }
        }
    }
}