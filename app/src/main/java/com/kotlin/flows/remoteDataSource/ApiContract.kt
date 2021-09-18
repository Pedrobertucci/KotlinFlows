package com.kotlin.flows.remoteDataSource

import com.kotlin.flows.BitcoinResponse
import retrofit2.http.GET

interface ApiContract {

    @GET("/v2/assets/bitcoin")
    suspend fun getData() : BitcoinResponse
}