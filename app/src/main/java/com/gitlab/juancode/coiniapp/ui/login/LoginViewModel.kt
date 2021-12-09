package com.gitlab.juancode.coiniapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitlab.juancode.coiniapp.data.repository.CoiniRepository
import com.gitlab.juancode.coiniapp.entity.UserId
import com.gitlab.juancode.coiniapp.preference.Preference
import com.gitlab.juancode.coiniapp.ui.common.Event
import kotlinx.coroutines.launch

class LoginViewModel(private val coiniRepository: CoiniRepository): ViewModel() {

    private val _loginLive = MutableLiveData<Event<UserId>>()
    val loginLive: LiveData<Event<UserId>> get() = _loginLive

    fun login(pinLocked: String) {
        viewModelScope.launch {
            val response = coiniRepository.login(Preference.savedPhoneNumber, pinLocked)
            Preference.userId = response.userId
            _loginLive.value = Event(response)
        }
    }
}