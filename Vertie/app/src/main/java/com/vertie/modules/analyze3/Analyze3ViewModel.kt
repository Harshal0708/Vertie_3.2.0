package com.vertie.modules.analyze3

import androidx.lifecycle.MutableLiveData
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject

class Analyze3ViewModel @Inject constructor() : BaseViewModel() {

    var goToAnalyze4 : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var feelingText : MutableLiveData<String> = MutableLiveData<String>()
    var back : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun back(){
        back.value = true
    }

    fun nextAnalysis(){
        goToAnalyze4.value = true
    }

}