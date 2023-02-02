package com.vertie.data.user.source

import com.vertie.data.base.BaseEmptyResponse
import com.vertie.data.user.LoginRequest
import com.vertie.data.user.LoginResponse
import com.vertie.data.user.User
import com.vertie.data.user.Userr
import io.reactivex.Completable
import io.reactivex.Single

interface UserDataSource {
    fun userLogin(loginRequest: LoginRequest): Single<LoginResponse>
    fun setUserPrefs(user : Userr,token :String,id :String) : Single<BaseEmptyResponse>
    fun initSession(): Completable
    fun clearPrefs() : Completable
}