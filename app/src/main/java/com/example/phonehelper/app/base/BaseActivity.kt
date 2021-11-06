package com.example.phonehelper.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.example.phonehelper.R
import com.example.phonehelper.app.extension.observe
import com.example.phonehelper.app.model.base.Exceptions
import com.example.phonehelper.app.model.base.HttpErrors

abstract class BaseActivity<B: ViewDataBinding, VM: BaseViewModel>: AppCompatActivity() {

    /**
     * @return layout-land resource id
     */
    abstract fun getLayoutId(): Int

    /**
     * @return viewModel
     */
    abstract fun getViewModel(): VM

    val binding: B by binding(getLayoutId())

    private var loading: FullScreenLoading? = null

    private fun <T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView(this, resId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initBaseObserver()
    }

    private fun initBaseObserver() {
        observe(getViewModel().showErrorLiveData) { errorMessage ->
            when (errorMessage) {
                HttpErrors.SERVICE_UNAVAILABLE.toString(), HttpErrors.NOT_FOUND.toString() -> {
                    BasicDialog(this, getString(R.string.generic_error)).create().show()
                }
                Exceptions.UNKNOWN_HOST_EXCEPTION -> {
                    BasicDialog(this, getString(R.string.text_no_internet)).create().show()
                }
                Exceptions.TIME_OUT_EXCEPTION -> {
                    BasicDialog(this, getString(R.string.timeout_message)).create().show()
                }
                else -> {
                    BasicDialog(this, errorMessage).create().show()
                }
            }
        }

        observe(getViewModel().showProgressBarLiveData) {
            if (it) {
                showLoading(supportFragmentManager)
            } else {
                hideLoading(supportFragmentManager)
            }
        }
    }

    internal fun showLoading(fm: FragmentManager) {
        loading = FullScreenLoading().apply {
            isCancelable = true
            show(fm, TAG_LOADING)
        }
    }

    internal fun hideLoading(fm: FragmentManager) {
        fm.fragments.filterIsInstance<FullScreenLoading>().forEach {
            it.dismiss()
        }
    }

    companion object {
        private const val TAG_LOADING = "Loading"
    }

}