package com.test.testapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testapplication.R
import com.test.testapplication.base.BaseActivity
import com.test.testapplication.base.HandleResponse
import com.test.testapplication.data.ErrorResponse
import com.test.testapplication.data.model.PlaceResponse
import com.test.testapplication.databinding.ActivityMainBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding?, MainViewModel>(),
    HasAndroidInjector {

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() {
            return getViewModel(MainViewModel::class.java)
        }

    private val PLACEID = 91741

    lateinit var linearLayoutManager: LinearLayoutManager
    private var placeAdapter = PlaceAdapter()

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        getPlaceInfo()
    }

    private fun initTitle(title: String){
        viewDataBinding?.tvTitle?.text = title
    }

    private fun initRecyclerView(){
        linearLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        with(viewDataBinding?.rvPlaceList!!){
            layoutManager = linearLayoutManager
            adapter = placeAdapter
        }
    }

    private fun getPlaceInfo(){
        viewModel.getPlaceInfo(PLACEID, object : HandleResponse<PlaceResponse> {
            override fun handleErrorResponse(error: ErrorResponse?) {

            }

            override fun handleSuccessResponse(successResponse: PlaceResponse) {

                initTitle(successResponse.country?:"" + successResponse.post_code?: "")

                if (successResponse.places!= null){
                    placeAdapter.setItems(successResponse.places)
                }
            }
        })
    }

}