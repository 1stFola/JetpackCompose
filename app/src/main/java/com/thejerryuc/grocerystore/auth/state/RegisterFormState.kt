package com.thejerryuc.grocerystore.auth.state

data class RegisterFormState(
    val email: String ="",
    val emailValid: Boolean = true,
    val emailErrorMessage: String = "",
    val password: String = "",
    val passwordValid: Boolean = true,
    val passwordErrorMessage:String = "",
    val firstname: String = "",
    val firstnameValid: Boolean = true,
    val firstnameErrorMessage: String = "",
    val lastname: String = "",
    val lastnameValid: Boolean = true,
    val lastnameErrorMessage: String = "",
    val formValid: Boolean = true
)
