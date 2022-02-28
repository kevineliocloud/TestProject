package com.test.testapplication.data.remote

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.testapplication.data.model.PlaceResponse
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object APIManager {
    private const val CACHE_FILE_NAME = "test_http_cache"


    private lateinit var apiInterface: ApiInterface

    fun init(context: Context) {

        // OKHttp
        val okHttpClient = buildOkHttpClient(Cache(buildAppCacheFile(context), 10 * 1000 * 1000))

        // Retrofit
        val retrofit = buildAppRetrofit(okHttpClient)

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    private fun buildAppCacheFile(context: Context): File{
        val cacheFile = File(context.cacheDir, CACHE_FILE_NAME)
        cacheFile.mkdir()
        return cacheFile
    }

    private fun buildOkHttpClient(cache: Cache): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor())
            .cache(cache)
            .build()
    }

    private fun buildAppRetrofit(okHttpClient : OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.zippopotam.us/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .client(okHttpClient).build()
    }

    // Google-API to do reverse Geo0coding
    fun getPlaceInfo(placeID: Int): Single<PlaceResponse> {
        return apiInterface.getPlaceInfo("https://api.zippopotam.us/us/$placeID")
    }
}