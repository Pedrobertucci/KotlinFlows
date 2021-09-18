package com.kotlin.flows.repository

import com.kotlin.flows.BitcoinResponse
import com.kotlin.flows.remoteDataSource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class BitcoinRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
){

    val lastBitcoinUpdate : Flow<BitcoinResponse> = remoteDataSource.lastUpdate
        .flowOn(Dispatchers.IO)
}