package com.vertie.data.user.source.local

import android.content.SharedPreferences
import com.vertie.data.user.User
import com.vertie.data.user.Userr
import javax.inject.Inject

class UserPrefrencesHelper @Inject constructor(
    private val preferences: SharedPreferences
) {
    companion object {
        private const val TAG = "UserPreferencesHelper"
    }

    private var editor: SharedPreferences.Editor = preferences.edit()

    fun getUser() : com.vertie.data.user.User?{
        val email : String? = preferences.getString(com.vertie.data.user.source.local.UserPersistanceContract.UserEntry.Companion.USER_EMAIL,null)
        val pass : String? = preferences.getString(com.vertie.data.user.source.local.UserPersistanceContract.UserEntry.Companion.USER_PASSWORD,null)
        return if(email == null){
            null
        }else{
            User(
                email = email,
                password = pass
            )
        }
    }

    fun setUser(user : Userr){
        editor.putString(UserPersistanceContract.UserEntry.USER_EMAIL,user.email).commit()
        editor.putString(UserPersistanceContract.UserEntry.USER_PASSWORD,user.password).commit()
        editor.putString(UserPersistanceContract.UserEntry.USER_ID,user.id).commit()
    }

    fun getToken() : String?{
        val token : String? = preferences.getString(UserPersistanceContract.UserEntry.USER_TOKEN,null)
        return if(token == null){
            null
        }else{
            token
        }
    }

    fun setToken(token : String){
        editor.putString(UserPersistanceContract.UserEntry.USER_TOKEN,token).commit()
    }

    fun setUserId(id : String){
        editor.putString(UserPersistanceContract.UserEntry.USER_ID,id).commit()
    }

    fun setUserName(name : String){
        editor.putString(UserPersistanceContract.UserEntry.USER_ID,name).commit()
    }

    fun setUserEmail(email : String){
        editor.putString(UserPersistanceContract.UserEntry.USER_ID,email).commit()
    }

    fun clearPrefs(){
        editor.clear()
    }

}