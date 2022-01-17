package com.mgstudio.phonehelper.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgstudio.phonehelper.app.model.base.Response
import com.google.gson.Gson
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

abstract class BaseViewModel : ViewModel() {

    private val _showProgressBarLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val showProgressBarLiveData: LiveData<Boolean> = _showProgressBarLiveData

    private val _showErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val showErrorLiveData: LiveData<String> = MutableLiveData()

    fun <T> launchViewModelScope(
        block: Flow<Response<T>>,
        isLoading: Boolean = true,
        collectData: ((Response<T>) -> Unit)
    ) {
        viewModelScope.launch {
            block.onStart {
                _showProgressBarLiveData.value = isLoading
            }.catch { exception ->
                parseExceptionMessage(exception)
            }.collect { response ->
                if(response.error.not()) {
                    collectData(response)
                } else {
                    _showErrorLiveData.value = response.message
                }
            }
        }
    }

    private fun parseExceptionMessage(ex: Throwable) {
        _showProgressBarLiveData.value = false
        if (ex is HttpException) {
            if (ex.response()?.errorBody() != null) {
                val response = Gson().fromJson(
                    ex.response()?.errorBody()?.string(),
                    Response::class.java
                )
                if (response != null) {
                    _showErrorLiveData.value = response.message
                }
            }
        } else {
            _showErrorLiveData.value = ex.message
        }
    }

}