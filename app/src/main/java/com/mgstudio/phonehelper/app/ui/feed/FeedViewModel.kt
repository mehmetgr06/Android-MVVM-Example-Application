package com.mgstudio.phonehelper.app.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mgstudio.phonehelper.app.base.BaseViewModel
import com.mgstudio.phonehelper.app.model.feed.FeedResponse
import com.mgstudio.phonehelper.app.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val feedRepository: FeedRepository) :
    BaseViewModel() {

    private val _historyLiveData: MutableLiveData<MutableList<FeedResponse>> = MutableLiveData()
    val historyLiveData: LiveData<MutableList<FeedResponse>> = _historyLiveData

    init {
        launchViewModelScope(feedRepository.getHistory()) { response ->
            response.response?.let { historyList ->
                _historyLiveData.value = historyList
            }
        }
    }

}