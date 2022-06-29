package com.thejerryuc.grocerystore.domain.usecases.auth

import android.util.Log
import com.thejerryuc.grocerystore.data.remote.request.RegisterRequest
import com.thejerryuc.grocerystore.data.remote.response.RegisterResponse
import com.thejerryuc.grocerystore.domain.repositories.UserRepo
import com.thejerryuc.grocerystore.general.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class Register(private val userRepo: UserRepo) {
    operator fun invoke(request: RegisterRequest): Flow<Resource<RegisterResponse>> =
        flow {
            try {
                emit(Resource.Loading<RegisterResponse>())
                var response = userRepo.register(request)
                emit(Resource.Success<RegisterResponse>(response))
            } catch (e: HttpException){

                emit(Resource.Error<RegisterResponse>(message = e.localizedMessage ?: "Something went wrong"))

            } catch (e: IOException){

                emit(Resource.Error<RegisterResponse>(
                    message = e.localizedMessage
                        ?: ("Could not reach server, " + "please check your internet")

                ))
            } catch (e: Exception){

                emit(Resource.Error<RegisterResponse>(
                    message = e.localizedMessage ?: "Something went wrong"
                ))

            }
        }
}