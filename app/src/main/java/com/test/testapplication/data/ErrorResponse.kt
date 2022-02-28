package com.test.testapplication.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class ErrorResponse : BaseResponse() {

    @SerializedName("data")
    @Expose
    var errorsMessages: ErrorsMessages? = null

}