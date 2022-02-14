package com.mgstudio.phonehelper.app.ui.feed

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.mgstudio.phonehelper.app.MainCoroutinesRule
import com.mgstudio.phonehelper.app.PhoneHelperApp
import com.mgstudio.phonehelper.app.model.base.ResponseService
import com.mgstudio.phonehelper.app.model.feed.FeedResponse
import com.mgstudio.phonehelper.app.network.PhoneHelperService
import com.mgstudio.phonehelper.app.repository.FeedRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class FeedViewModelTest {

    private lateinit var viewModel: FeedViewModel
    private val service: PhoneHelperService = mock()
    private var feedRepository: FeedRepository = mock()
    private lateinit var testScope: TestCoroutineScope

    @get:Rule
    var mainCoroutineRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        feedRepository = FeedRepository(service, mainCoroutineRule.testDispatcher)
        viewModel = FeedViewModel(feedRepository)
    }

    @Test
    fun getFeedHistory() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val feedResponse = mutableListOf<FeedResponse>()
        val response = ResponseService<FeedResponse>("false", "OK", mutableListOf(), null, feedResponse)
        mainCoroutineRule.testDispatcher.runBlockingTest {
            whenever(service.getHistory()).thenReturn(response)
            viewModel.getFeedHistory()
            Assert.assertEquals(viewModel.historyLiveData.value, feedResponse)
        }

    }

}

