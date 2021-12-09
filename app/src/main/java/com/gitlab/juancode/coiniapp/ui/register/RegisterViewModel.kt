package com.gitlab.juancode.coiniapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitlab.juancode.coiniapp.data.repository.CoiniRepository
import com.gitlab.juancode.coiniapp.data.repository.FlagRepository
import com.gitlab.juancode.coiniapp.entity.Flag
import com.gitlab.juancode.coiniapp.entity.Response
import com.gitlab.juancode.coiniapp.entity.User
import com.gitlab.juancode.coiniapp.preference.Preference
import com.gitlab.juancode.coiniapp.ui.common.Event
import kotlinx.coroutines.launch

class RegisterViewModel(private val flagRepository: FlagRepository, private val coiniRepository: CoiniRepository): ViewModel() {

    private val _flagsLive = MutableLiveData<List<Flag>>()
    val flagsLive: LiveData<List<Flag>>
        get() {
            if (_flagsLive.value == null) getFlags()
            return _flagsLive
        }

    private val _phoneLive = MutableLiveData<Event<Response>>()
    val phoneLive: LiveData<Event<Response>> get() = _phoneLive

    private val _enterButtonLive = MutableLiveData<Event<User>>()
    val enterButtonLive: LiveData<Event<User>> get() = _enterButtonLive

    private var phoneNumber = ""
    fun getFlags() {
        viewModelScope.launch {
            _flagsLive.value = flagRepository.getFlags()
        }
    }

    fun nextButtonVerificationClicked(code: String, phoneNumber: String) {
        this.phoneNumber = phoneNumber
        viewModelScope.launch {
            _phoneLive.value = Event(coiniRepository.verificationPhone(code, phoneNumber))
        }
    }

    fun enterButtonClicked(password: String) {
        viewModelScope.launch {
            _enterButtonLive.value = Event(coiniRepository.createUser(phoneNumber, password))
            Preference.savedPhoneNumber = phoneNumber

        }
    }
}