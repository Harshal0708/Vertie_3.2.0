package com.vertie.modules.analyze4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.vertie.R

import com.vertie.databinding.ActivityMainBinding
import com.vertie.modules.analyze3.Analyze3Activity
import com.vertie.modules.analyze3.Analyze3Fragment
import com.vertie.modules.base.BaseActivity
import com.vertie.modules.base.BaseFragment
import com.vertie.utils.ActivityUtils
import javax.inject.Inject

class Analyze4Activity : BaseActivity() {
    @Inject
    lateinit var injectedFragment: Analyze4Fragment
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if(fragment == null){
            fragment = injectedFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,fragment,binding.contentFrame.id,false
            )
        }
        setupToolbar(binding.toolbar.toolbar,R.string.analyze, null, R.drawable.ic_backbutton)
    }



    override fun refresh() {
        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment is BaseFragment) {
            fragment.refresh()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, Analyze4Activity::class.java)
        }
    }

}