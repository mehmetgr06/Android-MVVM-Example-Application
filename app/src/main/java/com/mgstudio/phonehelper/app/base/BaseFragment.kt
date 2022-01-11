package com.mgstudio.phonehelper.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.mgstudio.phonehelper.app.extension.observe

abstract class BaseFragment<B: ViewBinding, VM: BaseViewModel>: Fragment() {

    /**
     * @return layout-land resource id
     */
    abstract fun getLayoutId(): Int

    lateinit var binding: B

    /**
     * @return viewModel
     */
    abstract fun getViewModel(): VM

    private fun <T : ViewDataBinding> bind(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(
            inflater,
            getLayoutId(),
            container
        )

        observe(getViewModel().showProgressBarLiveData) {
            if(it) {
                (requireActivity() as BaseActivity<*,*>).showLoading(childFragmentManager)
            } else {
                (requireActivity() as BaseActivity<*,*>).hideLoading(childFragmentManager)
            }
        }

        return binding.root
    }


}