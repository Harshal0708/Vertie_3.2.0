package com.vertie.utils.liveDataUtils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created by Amr Salah on 5/21/2019.
 */
class MutableLiveDataNN<T : Any>(initValue: T) : MutableLiveData<T>() {

    // This class used for asserting non null values on LiveData

    init {
        value = initValue
    }

    override fun getValue(): T {
        return super.getValue()!!
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }

    fun observe(owner: LifecycleOwner, body: (T) -> Unit) {
        observe(owner, Observer<T> { t -> body(t!!) })
    }

    override fun postValue(value: T) {
        super.postValue(value)
    }
}