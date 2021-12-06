package com.gitlab.juancode.coiniapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitlab.juancode.coiniapp.data.repository.FlagRepository
import com.gitlab.juancode.coiniapp.entity.Flag
import kotlinx.coroutines.launch

class RegisterViewModel(private val flagRepository: FlagRepository): ViewModel() {

    private val _flagsLive = MutableLiveData<List<Flag>>()
    val flagsLive: LiveData<List<Flag>>
        get() {
            if (_flagsLive.value == null) getFlags()
            return _flagsLive
        }

    fun getFlags() {
        viewModelScope.launch {
            _flagsLive.value = flagRepository.getFlags()
        }
    }
}