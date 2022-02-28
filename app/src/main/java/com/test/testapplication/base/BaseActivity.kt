package com.test.testapplication.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.*
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.testapplication.BR
import com.test.testapplication.di.factory.ViewModelProviderFactory
import dagger.android.AndroidInjection
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding?, V : BaseViewModel> : AppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory


    var progressView: View? = null

    var viewDataBinding: T? = null
        private set

    private var mViewModel: V? = null
    private var deviceID: String =""
    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    private val bindingVariable: Int = BR._all

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }


    protected open fun <T : ViewModel> getViewModel(cls: java.lang.Class<T>): T {
        return ViewModelProvider(this)[cls]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    override fun onBackPressed() {
        if (viewModel.isLoading.value == false) {
            super.onBackPressed()
        }
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    fun showLoading() {
        if (progressView != null) {
            progressView!!.visibility = View.VISIBLE
        }
    }

    fun hideLoading() {
        if (progressView != null) {
            progressView!!.visibility = View.GONE
        }
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView<T>(this, layoutId)
        mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
        mViewModel?.isLoading!!.observe(this, Observer { consumeResponse(it) })

    }

    open fun consumeResponse(o: Boolean) {
        if (o) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    fun onError(message: String) {
    }


    override fun startActivity(intent: Intent){
        super.startActivity(intent)
    }


    fun getDeviceID() : String{
        if (deviceID.isEmpty()){
            deviceID = Secure.getString(this.contentResolver,
                Secure.ANDROID_ID)
        }

        return deviceID
    }

}