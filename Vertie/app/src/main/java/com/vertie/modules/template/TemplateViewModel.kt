package com.vertie.modules.template

import android.content.Context
import com.vertie.modules.base.BaseViewModel
import com.vertie.utils.connectivityUtils.OnlineChecker
import com.vertie.utils.providers.BaseResourceProvider
import javax.inject.Inject

class TemplateViewModel @Inject constructor(
        private val applicationContext: Context)
    : BaseViewModel() {
}