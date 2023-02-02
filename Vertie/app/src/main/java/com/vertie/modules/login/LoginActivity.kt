package com.vertie.modules.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.vertie.R
import com.vertie.databinding.ActivityMainBinding
import com.vertie.databinding.ActivityNoToolbarBinding
import com.vertie.modules.base.BaseActivity
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.template.TemplateActivity
import com.vertie.modules.template.TemplateFragment
import com.vertie.utils.ActivityUtils
import javax.inject.Inject

class LoginActivity : BaseActivity(){

    @Inject
    lateinit var injectedFragment: LoginFragment
    private lateinit var binding: ActivityNoToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_no_toolbar)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if(fragment == null){
            fragment = injectedFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager, fragment, binding.contentFrame.id,false
            )
        }

    }

    override fun refresh() {
        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment is BaseFragment) {
            fragment.refresh()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

}