package com.vertie.modules.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vertie.Constants
import com.vertie.utils.liveDataUtils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.CompositeException

abstract class BaseViewModel() : ViewModel() {

    val disposable = CompositeDisposable()
    val snackMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val toastMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val actionLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun handleDataError(tag: String, error: Throwable) {
        Log.e(tag,"Exception ${Log.getStackTraceString(error)}")
        loading.value = false
        if (error is CompositeException) {
            val customErrors = error.exceptions.filter { exception -> exception.cause?.message == Constants.CUSTOM_ERROR }
            if (customErrors.isNotEmpty()) {
                snackMessage.value = customErrors[0].cause?.cause?.message
            }
        }
    }
    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}