package com.mgstudio.phonehelper.app.ui.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.mgstudio.phonehelper.R
import com.mgstudio.phonehelper.app.base.BaseFragment
import com.mgstudio.phonehelper.app.base.MagicRecyclerAdapter
import com.mgstudio.phonehelper.app.extension.observe
import com.mgstudio.phonehelper.app.model.feed.FeedResponse
import com.mgstudio.phonehelper.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding, FeedViewModel>() {

    private val feedViewModel: FeedViewModel by activityViewModels()

    private val caseHistoryAdapter =
        MagicRecyclerAdapter<Any, FeedResponse>(R.layout.item_history_feed)

    override fun getLayoutId(): Int = R.layout.fragment_feed

    override fun getViewModel(): FeedViewModel = feedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        binding.apply {
            with(feedViewModel) {
                observe(historyLiveData) { historyList ->
                    caseHistoryAdapter.setData(historyList)
                    rvCases.adapter = caseHistoryAdapter
                }
            }
        }
    }

}