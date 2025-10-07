package com.example.application_1.fragment_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class SecondFragmentViewModel : ViewModel() {
    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> = _navigationEvent

    private val _screenTitle = MutableLiveData<String>()
    val screenTitle: LiveData<String> = _screenTitle

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> = _successMessage

    init {
        // initialisation data after create ViewModel
        _screenTitle.value = "Success Screen"
        _successMessage.value = "Welcome! Login successful"
    }

    fun loadUserData() {
        _screenTitle.value = "Success Screen"
        _successMessage.value = "User data loaded successfully!"
    }


    // navigation back
    fun onBackButtonClicked() {
        _navigationEvent.value = true
    }

    // reset data of navigation
    fun navigationHandled() {
        _navigationEvent.value = false
    }
}