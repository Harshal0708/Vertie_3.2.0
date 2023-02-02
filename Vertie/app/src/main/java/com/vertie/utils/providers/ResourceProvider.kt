package com.vertie.utils.providers

import android.content.Context
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import com.google.common.base.Preconditions

/**
 * Created by sarahussien on 2/21/19.
 */
class ResourceProvider(@NonNull context: Context) : BaseResourceProvider {

    @NonNull
    private val mContext: Context

    init {
        mContext = Preconditions.checkNotNull(context, "context cannot be null")
    }

    @NonNull
    override fun getString(@StringRes id: Int): String {
        return mContext.getString(id)
    }

    @NonNull
    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return mContext.getString(resId, *formatArgs)
    }
}