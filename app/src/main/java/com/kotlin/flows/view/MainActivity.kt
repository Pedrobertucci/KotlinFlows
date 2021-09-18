package com.kotlin.flows.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.kotlin.flows.R
import com.kotlin.flows.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupObservers()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.bitcoinLiveData.observe(this, {
            it?.let {
                binding.coinData = it.coinData
            } ?: run {
                Toast.makeText(
                    this,
                    this.resources.getText(R.string.data_empty),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.errorLiveData.observe(this, {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(
                    this,
                    this.resources.getText(R.string.unknown_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}