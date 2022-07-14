package com.haroldcalayan.conversionrates.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import com.haroldcalayan.conversionrates.R
import com.haroldcalayan.conversionrates.common.base.BaseActivity
import com.haroldcalayan.conversionrates.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    ConversionAdapter.ConversionAdapterListener {

    override val layoutId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private lateinit var conversionAdapter: ConversionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observe()
    }

    override fun initViews() {
        super.initViews()

        binding.edittextFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.filterConversionRates(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.imageviewRefresh.setOnClickListener {
            viewModel.getConversionRates()
        }

        initConversionList()
    }

    override fun observe() {
        super.observe()
        viewModel.filteredConversionRates.observe(this, {
            conversionAdapter.updateData(it)
        })
    }

    private fun initConversionList() {
        conversionAdapter = ConversionAdapter(emptyList(), this)
        binding.recyclerviewConversions.adapter = conversionAdapter
        viewModel.getConversionRates()
    }

    override fun onItemClick(item: String) {
    }
}