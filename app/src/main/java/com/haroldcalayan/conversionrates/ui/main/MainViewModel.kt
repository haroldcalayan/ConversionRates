package com.haroldcalayan.conversionrates.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldcalayan.conversionrates.common.base.BaseViewModel
import com.haroldcalayan.conversionrates.data.repository.MobimateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mobimateRepository: MobimateRepository
) : BaseViewModel() {

    private val _getConversionRatesResponse = MutableLiveData<List<String>>()

    private val _filteredConversionRates = MutableLiveData<List<String>>()
    val filteredConversionRates: LiveData<List<String>> = _filteredConversionRates

    fun getConversionRates() {
        invoke {
            _getConversionRatesResponse.value = mobimateRepository.getConversionRates()
            _filteredConversionRates.postValue(_getConversionRatesResponse.value)
        }
    }

    fun filterConversionRates(filter: String? = "") {
        val conversionList = _getConversionRatesResponse.value
        if (filter.isNullOrEmpty()) {
            _filteredConversionRates.postValue(conversionList)
        } else {
            _filteredConversionRates.postValue(conversionList.orEmpty().filter { it.toLowerCase().contains(filter) })
        }
    }
}