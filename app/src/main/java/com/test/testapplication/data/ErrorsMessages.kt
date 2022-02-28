package com.test.testapplication.data

import android.annotation.SuppressLint
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

@SuppressLint("ParcelCreator")
class ErrorsMessages {
    @SerializedName("name")
    @Expose
    var name: ArrayList<String>? = null

    @SerializedName("email")
    @Expose
    var email: ArrayList<String>? = null

    @SerializedName("mobile")
    @Expose
    var mobile: ArrayList<String>? = null
}