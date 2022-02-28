package com.test.testapplication.ui.main

import com.google.gson.JsonObject
import com.test.testapplication.base.BaseViewModel
import com.test.testapplication.base.HandleResponse
import com.test.testapplication.data.model.PlaceResponse
import com.test.testapplication.data.remote.APIManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {

    fun getPlaceInfo(placeID : Int,     handleResponse: HandleResponse<PlaceResponse>){
        compositeDisposable.add(
            APIManager.getPlaceInfo(placeID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        run {
                            handleResponse.handleSuccessResponse(result)
                        }
                    },
                    { errorResult ->
                        run {
                            handleResponse.handleErrorResponse(getThrowableError(errorResult))
                        }
                    }
                ))
    }

}