package com.test.testapplication.data.remote

import com.test.testapplication.data.model.PlaceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getPlaceInfo(@Url url: String): Single<PlaceResponse>
}