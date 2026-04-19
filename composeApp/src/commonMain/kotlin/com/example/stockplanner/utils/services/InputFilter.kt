package com.example.stockplanner.utils.services

data class Resp (
    val success: Boolean,
    val errorMessage: String
)

class InputFilter {

    fun validateLogin(userName: String, password: String): Resp {
        var error = "";
        var success = false;

        if (userName.isBlank() && password.isBlank()) {
            error = "Username or Password should not be empty!"
            return Resp(success, error)
        }
        if (userName.length < 5 || password.length < 5) {
            error = "Username or Password length must be minimum 5 characters"
            return Resp(success, error)
        }
        success = true;
        return Resp(errorMessage = error, success = success)
    }
}