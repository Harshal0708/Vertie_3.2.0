package com.vertie.modules.analyze1

import androidx.lifecycle.MutableLiveData
import com.vertie.data.mood.Mood
import com.vertie.data.mood.source.MoodDataSource
import com.vertie.data.mood.source.MoodRepositoryModule
import com.vertie.di.qualifiers.Repo
import com.vertie.modules.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Analyze1ViewModel @Inject constructor(
    @Repo var moodRepositoryModule : MoodDataSource
) : BaseViewModel() {

    var goToAnalyze2 : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var mood : MutableLiveData<String> = MutableLiveData<String>()

    private fun nextAnalysis(){
        goToAnalyze2.value = true
    }

    fun setMood(moodInput : String){
        mood.value = moodInput
    }

    fun saveMood(){
        mood.value?.let { Mood(it) }?.let {
            moodRepositoryModule.setMood(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mood.value = ""
                        nextAnalysis()
                    },{
                        handleDataError("tag",it)
                    }
                )
        }?.let {
            disposable.add(
                it
            )
        }
    }


}