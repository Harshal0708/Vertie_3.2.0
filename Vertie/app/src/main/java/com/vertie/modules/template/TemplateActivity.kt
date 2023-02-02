package com.vertie.modules.template

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.vertie.R
import com.vertie.modules.base.BaseActivity
import com.vertie.modules.base.BaseFragment
import com.vertie.databinding.ActivityMainBinding
import com.vertie.utils.ActivityUtils
import javax.inject.Inject

class TemplateActivity : BaseActivity() {

    @Inject
    lateinit var injectedFragment: TemplateFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment == null) {
            fragment = injectedFragment
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager, fragment, binding.contentFrame.id,false)

        }
    }

    override fun refresh() {
        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment is BaseFragment) {
            fragment.refresh()
        }
        // TODO( "Handle Activity refresh if applicable")
    }
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TemplateActivity::class.java)
        }
    }
}