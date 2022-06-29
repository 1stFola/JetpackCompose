package com.thejerryuc.grocerystore.di

import com.thejerryuc.grocerystore.data.remote.Api
import com.thejerryuc.grocerystore.data.repositories.UserRepoImpl
import com.thejerryuc.grocerystore.domain.repositories.UserRepo
import com.thejerryuc.grocerystore.domain.usecases.auth.AuthenticationUseCases
import com.thejerryuc.grocerystore.domain.usecases.auth.Login
import com.thejerryuc.grocerystore.domain.usecases.auth.Register
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val baseUrl = "https://gorceriesonline.herokuapp.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun providesGroceriesApi(client: OkHttpClient): Api {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepo(api: Api): UserRepo{
        return UserRepoImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repo: UserRepo): AuthenticationUseCases{
        return AuthenticationUseCases(login = Login(userRepo = repo), register = Register(userRepo = repo))
    }
}