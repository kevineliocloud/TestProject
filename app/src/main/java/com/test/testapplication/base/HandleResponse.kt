package com.test.testapplication.base

import com.test.testapplication.data.ErrorResponse


interface HandleResponse<T> {

    fun handleErrorResponse(error: ErrorResponse?)
    fun handleSuccessResponse(successResponse: T)

}