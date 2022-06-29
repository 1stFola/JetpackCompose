package com.thejerryuc.grocerystore.data.remote.request

data class RegisterRequest(
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String
)
