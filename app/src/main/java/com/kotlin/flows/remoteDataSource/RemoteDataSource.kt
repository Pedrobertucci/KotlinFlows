package com.kotlin.flows.remoteDataSource

import com.kotlin.flows.BitcoinResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiContract: ApiContract,
    private val refreshIntervalMs: Long = 30000) {

    val lastUpdate : Flow<BitcoinResponse> = flow {
        while (true) {
            val lastUpdate = apiContract.getData()
            emit(lastUpdate)
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.IO)
}