package com.example.stockplanner.utils.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//
//class AppViewModel : ViewModel() {
//    private val _isLoggedIn = MutableStateFlow(false)
//    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
//
//    fun login() {
//        _isLoggedIn.value = true
//    }
//
//    fun logout() {
//        _isLoggedIn.value = false
//    }
//}

class AppState {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    var userName: String = "";
    var password: String = "";

    fun login(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            _isLoggedIn.value = true
            this.userName = userName
            this.password = password
        }
    }

    fun logout() {
        _isLoggedIn.value = false
    }

    fun signup(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            _isLoggedIn.value = true
            this.userName = userName
            this.password = password
        }
    }

    fun signout() {
        _isLoggedIn.value = false
    }
}