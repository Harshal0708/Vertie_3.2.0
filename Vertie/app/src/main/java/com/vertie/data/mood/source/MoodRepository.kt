package com.vertie.data.mood.source

import com.vertie.data.mood.Mood
import com.vertie.data.user.source.UserDataSource
import com.vertie.di.qualifiers.Local
import com.vertie.di.qualifiers.Remote
import com.vertie.utils.providers.BaseResourceProvider
import io.reactivex.Single
import javax.inject.Inject

class MoodRepository @Inject constructor(@Local val local: MoodDataSource, @Remote val remote: MoodDataSource, private val resourceProvider: BaseResourceProvider) : MoodDataSource {

    override fun setMood(mood: Mood): Single<Unit> {
        return local.setMood(mood)
    }

    override fun getMood(): Single<Mood> {
        return local.getMood()
    }
}