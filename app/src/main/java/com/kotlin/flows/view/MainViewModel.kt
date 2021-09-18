package com.kotlin.flows.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.flows.BitcoinResponse
import com.kotlin.flows.repository.BitcoinRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val bitcoinRepository: BitcoinRepository) : ViewModel() {
    private val bitcoinMutableData = MutableLiveData<BitcoinResponse>()
    val bitcoinLiveData: LiveData<BitcoinResponse> get() = bitcoinMutableData

    private val errorMutableData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = errorMutableData

    init {
        getBitcoinPrice()
    }

    private fun updateBitcoinPrice(bitcoinPrice: BitcoinResponse) {
        Log.d("MainViewModel", "updateBitcoinPrice: $bitcoinPrice")
        bitcoinMutableData.value = bitcoinPrice
    }

    private fun notifyError(exception: Throwable) {
        Log.d("MainViewModel", "notifyError: ${exception.message}")
        errorMutableData.value = exception.message
        getBitcoinPrice()
    }

    private fun getBitcoinPrice() {
        viewModelScope.launch {
            bitcoinRepository.lastBitcoinUpdate
                .catch { exception -> notifyError(exception) }
                .collect { bitcoinPrice -> updateBitcoinPrice(bitcoinPrice) }
        }
    }
}