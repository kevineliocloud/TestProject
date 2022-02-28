package com.test.testapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Place(
    val latitude: String?,
    val longitude: String?,
    @SerializedName("place name")
    @Expose
    val placeName: String?,
    val state: String?,
    @SerializedName("state abbreviation")
    @Expose
    val state_abbreviation: String?
)