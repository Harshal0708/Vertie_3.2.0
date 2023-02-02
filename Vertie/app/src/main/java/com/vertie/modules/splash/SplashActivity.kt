package com.vertie.modules.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.vertie.R
import com.vertie.databinding.ActivityNoToolbarBinding
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseNotificationClickedLauncherActivity
import com.vertie.utils.ActivityUtils
import java.util.*
import javax.inject.Inject

class SplashActivity : BaseNotificationClickedLauncherActivity() {

    @Inject
    lateinit var injectedFragment: SplashFragment
    private lateinit var binding: ActivityNoToolbarBinding
    var notificationData: HashMap<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_no_toolbar)
        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment == null) {
            fragment = injectedFragment
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager, fragment, binding.contentFrame.id, false)
        }
    }

    override fun refresh() {
        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment is BaseFragment) {
            fragment.refresh()
        }
    }

    override fun pushNotificationClicked(data: HashMap<String, String>) {
        notificationData = data
    }
}