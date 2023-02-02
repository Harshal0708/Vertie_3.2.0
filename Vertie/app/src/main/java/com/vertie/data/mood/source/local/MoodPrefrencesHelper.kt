package com.vertie.data.mood.source.local

import android.content.SharedPreferences
import com.vertie.data.mood.Mood
import com.vertie.data.user.source.local.UserPersistanceContract
import io.reactivex.Single
import javax.inject.Inject

class MoodPrefrencesHelper @Inject constructor(
    private val preferences: SharedPreferences
) {

    companion object{
        private const val TAG = "MoodPreferencesHelper"
    }

    private var editor: SharedPreferences.Editor = preferences.edit()

    fun getMood():Single<Mood>{
        val status : String? = preferences.getString(MoodPersistanceContract.MoodEntry.MOOD_STATUS,null)
        return if(status == null){
            Single.fromCallable{ null }
        }else{
            Single.fromCallable{ Mood(status) }
        }
    }

    fun setMood(mood: Mood){
        editor.putString(MoodPersistanceContract.MoodEntry.MOOD_STATUS,mood.mood).commit()
    }
}