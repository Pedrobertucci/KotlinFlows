package com.kotlin.flows

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BitcoinResponse(
    @SerializedName("data")
    val coinData: CoinData?,
    val timestamp: Long?
) : Parcelable {

    override fun toString(): String {
        return "BitcoinResponse(coinData=$coinData, timestamp=$timestamp)"
    }
}

@Parcelize
data class CoinData(
    val id: String?,
    val rank: String?,
    val symbol: String?,
    val name: String?,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?,
    val volumeUsd24Hr: String?,
    val priceUsd: String?,
    val changePercent24Hr: String?,
    val vwap24Hr: String?,
    val explorer: String?
) : Parcelable {
    override fun toString(): String {
        return "CoinData(id=$id, rank=$rank, symbol=$symbol, name=$name, supply=$supply, " +
                "maxSupply=$maxSupply, marketCapUsd=$marketCapUsd, volumeUsd24Hr=$volumeUsd24Hr, " +
                "priceUsd=$priceUsd, changePercent24Hr=$changePercent24Hr, vwap24Hr=$vwap24Hr, " +
                "explorer=$explorer)"
    }
}
