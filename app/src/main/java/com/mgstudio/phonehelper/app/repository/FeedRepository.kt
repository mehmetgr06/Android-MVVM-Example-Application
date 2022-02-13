package com.mgstudio.phonehelper.app.repository

import com.mgstudio.phonehelper.app.di.IoDispatcher
import com.mgstudio.phonehelper.app.model.base.ResponseService
import com.mgstudio.phonehelper.app.model.feed.FeedResponse
import com.mgstudio.phonehelper.app.network.PhoneHelperService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepository @Inject constructor(
    private val service: PhoneHelperService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun getHistory(): Flow<ResponseService<FeedResponse>> {
        return flow {
            emit(service.getHistory())
        }.flowOn(ioDispatcher)
    }

}