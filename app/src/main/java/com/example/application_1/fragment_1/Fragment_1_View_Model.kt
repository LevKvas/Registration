package com.example.application_1.fragment_1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class FirstFragmentViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> = _navigationEvent

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val _loginState = MutableLiveData<String>()
    val loginState: LiveData<String> = _loginState

    private val _passwordState = MutableLiveData<String>()
    val passwordState: LiveData<String> = _passwordState

    // check data
    fun validateCredentials(login: String, password: String) {
        val isValid = checkData(password, login)

        if (isValid) {
            _navigationEvent.value = true // allowing navigation
            _showError.value = false
        } else {
            _showError.value = true
            _navigationEvent.value = false
        }
    }

    fun updateLogin(login: String) {
        _loginState.value = login
        _showError.value = false // hiding the error when changing the text
    }

    fun updatePassword(password: String) {
        _passwordState.value = password
        _showError.value = false
    }

    // check function
    private fun checkData(getPassword: String, getLogin: String): Boolean {
        val correctPass = "LandayTheory"
        val correctLogin = "Lev"
        return (getPassword == correctPass) && (correctLogin == getLogin)
    }

    // Resetting the navigation event after use
    fun navigationHandled() {
        _navigationEvent.value = false
    }
}