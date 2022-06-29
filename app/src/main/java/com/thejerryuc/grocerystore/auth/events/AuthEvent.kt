package com.thejerryuc.grocerystore.auth.events


sealed class AuthEvent{
    data class Login(
        val email: String,
        val password: String
    ) : AuthEvent()

    object LoginSuccess : AuthEvent()

    data class Register(
        val email: String,
        val password: String,
        val firstname: String,
        val lastname: String
    ) : AuthEvent()

    object RegisterSuccess : AuthEvent()
}
