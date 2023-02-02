package com.vertie.data.user.source

import com.vertie.data.user.LoginRequest
import com.vertie.data.user.LoginResponse
import com.vertie.data.user.User
import android.util.JsonToken
import com.vertie.data.base.BaseEmptyResponse
import com.vertie.data.user.Userr
import com.vertie.di.qualifiers.Local
import com.vertie.di.qualifiers.Remote
import com.vertie.di.scopes.AppScope
import com.vertie.utils.providers.BaseResourceProvider
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@AppScope
class UserRepository @Inject constructor(@Local val local: UserDataSource,@Remote val remote: UserDataSource, private val resourceProvider: BaseResourceProvider) : UserDataSource {


    override fun userLogin(loginRequest: LoginRequest): Single<LoginResponse> = remote.userLogin(loginRequest)
    override fun setUserPrefs(user: Userr,token: String,id :String): Single<BaseEmptyResponse> {
        local.setUserPrefs(user,token,id)
        initSession()
        return Single.just(BaseEmptyResponse())
    }

    override fun initSession(): Completable {
        return local.initSession()
    }

    override fun clearPrefs(): Completable = local.clearPrefs()
}