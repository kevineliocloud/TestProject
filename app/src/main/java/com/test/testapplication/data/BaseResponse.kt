package com.test.testapplication.data

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @field:SerializedName("httpCode")
    var code: Int? = 0

    @field:SerializedName("locale")
    var locale: String? = null

    @field:SerializedName("Message")
    var message: String? = null

    @SerializedName("country_flag_url")
    var countryFlag: String = ""

    @SerializedName("currency_symbol")
    var currency: String = "JD"

    override fun toString(): String {
        return "BaseResponse(code=$code, locale=$locale, message=$message)"
    }


}
