package com.vertie.modules.analyze4

import androidx.lifecycle.MutableLiveData
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject

class Analyze4ViewModel @Inject constructor() : BaseViewModel() {
    var goToNextCaffeine : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var goToNextNicotine : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var back : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun back(){
        back.value = true
    }

    fun caffeine(){
        goToNextCaffeine.value = true
    }

    fun nicotine(){
        goToNextNicotine.value = true
    }


}