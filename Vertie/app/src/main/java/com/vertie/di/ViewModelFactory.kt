package com.vertie.di

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vertie.di.scopes.AppScope
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by sarahussien on 2/13/19.
 */
@AppScope
class ViewModelFactory @Inject
constructor(private val mProviderMap: Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @NonNull
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        return mProviderMap[modelClass]!!.get() as T
    }
}