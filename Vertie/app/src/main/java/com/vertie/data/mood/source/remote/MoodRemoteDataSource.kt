package com.vertie.data.mood.source.remote

import com.vertie.data.mood.Mood
import com.vertie.data.mood.source.MoodDataSource
import com.vertie.data.user.source.remote.UserApiService
import io.reactivex.Single
import javax.inject.Inject

class MoodRemoteDataSource @Inject constructor(private val api: MoodApiService) : MoodDataSource {
    override fun setMood(mood: Mood): Single<Unit> {
        TODO("Not yet implemented")
    }

    override fun getMood(): Single<Mood> {
        TODO("Not yet implemented")
    }
}