package com.test.testapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    val country: String?,
    @SerializedName("country abbreviation")
    @Expose
    val country_abbreviation: String?,
    val places: List<Place>?,
    @SerializedName("post code")
    @Expose
    val post_code: String?
)