package com.test.testapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.testapplication.data.ErrorResponse
import com.test.testapplication.utils.AppLogger
import com.test.testapplication.utils.NetworkUtils
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList


abstract class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    private val errorResLiveData = MutableLiveData<ErrorResponse>()


    var compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()

    }

    fun setIsLoading(isLoading: Boolean) {
        AppLogger.d("isLoading $isLoading")
        this.isLoading.value = (isLoading)
    }

    fun getThrowableError(t: Throwable): ErrorResponse? {
        return NetworkUtils.getThrowableError(t)
    }


    private fun listToString(erorr: ArrayList<String>?): String? {
        val errMessage = StringBuilder("\n")
        if (erorr == null || erorr.isEmpty()) return errMessage.toString()
        erorr.forEach {
            errMessage.append("- ").append(it).append("\n")
        }

        return errMessage.toString()
    }

    open fun listToString(errorParser: ErrorResponse): String? {
        if (errorParser.errorsMessages == null) {
            return errorParser.message
        }
        var errorMMessages: String = ""

        errorMMessages += listToString(errorParser.errorsMessages!!.name)
        errorMMessages += listToString(errorParser.errorsMessages!!.email)
        errorMMessages += listToString(errorParser.errorsMessages!!.mobile)

        errorMMessages.trim { it <= ' ' }
        return errorMMessages
    }
}