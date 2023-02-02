package com.vertie.data.user.source.local

import com.vertie.data.base.BaseEmptyResponse
import com.vertie.data.user.*
import com.vertie.data.user.source.UserDataSource
import com.vertie.di.qualifiers.Local
import com.vertie.di.scopes.AppScope
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@AppScope
class UserLocalDataSource @Inject constructor(private val prefrencesHelper: UserPrefrencesHelper, private val session: Session) : UserDataSource{
    override fun userLogin(loginRequest: LoginRequest): Single<LoginResponse> {
        TODO("Not yet implemented")
    }

    override fun setUserPrefs(user: Userr,token :String, id: String): Single<BaseEmptyResponse> {
        prefrencesHelper.setUser(user)
        prefrencesHelper.setToken(token)
        prefrencesHelper.setUserId(id)
        return Single.just(BaseEmptyResponse())
    }

    override fun initSession(): Completable {
        session.currentUser = prefrencesHelper.getUser()
        session.token = prefrencesHelper.getToken()
        return Completable.complete()
    }

    override fun clearPrefs(): Completable {
        prefrencesHelper.clearPrefs()
        session.token = null
        session.currentUser = null
        return Completable.complete()
    }
}