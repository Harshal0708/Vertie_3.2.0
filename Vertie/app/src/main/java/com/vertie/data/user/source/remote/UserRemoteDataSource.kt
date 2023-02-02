package com.vertie.data.user.source.remote

import com.vertie.data.base.BaseEmptyResponse
import com.vertie.data.user.LoginRequest
import com.vertie.data.user.LoginResponse
import com.vertie.data.user.User
import com.vertie.data.user.Userr
import com.vertie.data.user.source.UserDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val api: UserApiService) : UserDataSource{
    
    override fun userLogin(loginRequest: LoginRequest): Single<LoginResponse> = api.login(loginRequest)

    override fun setUserPrefs(user: Userr,token :String,id:String): Single<BaseEmptyResponse> {
        TODO("Not yet implemented")
    }

    override fun initSession(): Completable {
        TODO("Not yet implemented")
    }

    override fun clearPrefs(): Completable {
        TODO("Not yet implemented")
    }
}

