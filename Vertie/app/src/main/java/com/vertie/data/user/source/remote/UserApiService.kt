package com.vertie.data.user.source.remote

import com.vertie.data.user.LoginRequest
import com.vertie.data.user.LoginResponse
import com.vertie.Constants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST(Constants.LOGIN)
    fun login(@Body request: LoginRequest): Single<LoginResponse>

}