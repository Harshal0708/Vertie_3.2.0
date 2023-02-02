package com.vertie.utils.providers

import androidx.annotation.NonNull
import androidx.annotation.StringRes

/**
 * Created by sarahussien on 2/21/19.
 */
interface BaseResourceProvider {

    /**
     * Resolves text's id to String.
     *
     * @param id to be fetched from the resources
     * @return String representation of the {@param id}
     */
    @NonNull
    fun getString(@StringRes id: Int): String

    /**
     * Resolves text's id to String and formats it.
     *
     * @param resId      to be fetched from the resources
     * @param formatArgs format arguments
     * @return String representation of the {@param resId}
     */
    @NonNull
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}