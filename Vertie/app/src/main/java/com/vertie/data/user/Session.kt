package com.vertie.data.user

import com.vertie.di.scopes.AppScope
import javax.inject.Inject


@AppScope
class Session @Inject constructor(){

    var currentUser: User? = null
    var token: String? = null

}