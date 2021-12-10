package com.gitlab.juancode.coiniapp.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitlab.juancode.coiniapp.data.repository.CoiniRepository
import com.gitlab.juancode.coiniapp.entity.Balance
import com.gitlab.juancode.coiniapp.preference.Preference
import kotlinx.coroutines.launch

class TransactionViewModel(private val coiniRepository: CoiniRepository): ViewModel() {
    private val _balanceLive =  MutableLiveData<Balance>()

    val balanceLive: LiveData<Balance>
        get() {
            if (_balanceLive.value == null) {
                getBalance()
            }
            return _balanceLive
        }

    private fun getBalance() {
        viewModelScope.launch {
            val response = coiniRepository.getBalance(Preference.userId)
            _balanceLive.value = response
        }
    }
}