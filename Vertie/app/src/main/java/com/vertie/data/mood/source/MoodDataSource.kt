package com.vertie.data.mood.source

import com.vertie.data.mood.Mood
import io.reactivex.Single

interface MoodDataSource {
    fun setMood(mood: Mood) : Single<Unit>
    fun getMood() : Single<Mood>
}