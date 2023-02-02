package com.vertie.utils.extenstions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vertie.utils.liveDataUtils.SingleLiveEvent

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}


fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
    val result = SingleLiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}