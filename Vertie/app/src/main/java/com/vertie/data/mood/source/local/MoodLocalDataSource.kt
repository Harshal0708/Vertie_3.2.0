package com.vertie.data.mood.source.local

import com.vertie.data.mood.Mood
import com.vertie.data.mood.source.MoodDataSource
import com.vertie.data.user.source.local.UserPrefrencesHelper
import io.reactivex.Single
import javax.inject.Inject

class MoodLocalDataSource @Inject constructor(private val prefrencesHelper: MoodPrefrencesHelper) : MoodDataSource {
    override fun setMood(mood: Mood): Single<Unit> {
       return Single.fromCallable{ prefrencesHelper.setMood(mood) }
    }

    override fun getMood(): Single<Mood> = prefrencesHelper.getMood()

}