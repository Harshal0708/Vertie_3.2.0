package com.vertie.modules.analyze2

import androidx.lifecycle.MutableLiveData
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject

class Analyze2ViewModel @Inject constructor() : BaseViewModel(){

    var goToAnalyze3 : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var back : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun back(){
        back.value = true
    }

    fun nextAnalysis(){
        goToAnalyze3.value = true
    }
}