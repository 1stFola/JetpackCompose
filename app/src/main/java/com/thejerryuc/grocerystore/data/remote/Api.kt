package com.thejerryuc.grocerystore.data.remote

import com.thejerryuc.grocerystore.data.remote.request.LoginRequest
import com.thejerryuc.grocerystore.data.remote.request.RegisterRequest
import com.thejerryuc.grocerystore.data.remote.response.LoginResponse
import com.thejerryuc.grocerystore.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest) : LoginResponse

    @POST("api/v1/auth/register")
    suspend fun register(@Body request: RegisterRequest) : RegisterResponse
}