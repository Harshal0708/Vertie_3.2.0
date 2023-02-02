package com.vertie.modules.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import com.vertie.databinding.FragmentSplashBinding
import javax.inject.Inject
import com.vertie.modules.dashboard.DashboardActivity
import com.vertie.modules.login.LoginActivity


class SplashFragment @Inject constructor() : BaseFragment() {
    private val TAG = this.javaClass.simpleName
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentSplashBinding

    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        //viewModel.initTimer()
        startInit()
    }

    private fun observeViewModel() {

        viewModel.sessionDidInit.observe(viewLifecycleOwner, Observer { sessionDidInit ->
            if(sessionDidInit){
                viewModel.checkUser(activity as? SplashActivity)
                viewModel.sessionDidInit.value = false
            }else{

            }
        }
        )

        viewModel.navigateToDashboard.observe(viewLifecycleOwner, Observer {  navigate ->
            if (navigate) {
                if ((activity as? SplashActivity)?.notificationData != null) {
                    // go to the dashboard with notificationData
                    //navigateToDashboardWithNotification((activity as? SplashActivity)?.notificationData!!)
                } else {
                    navigateToDashboard()
                }
                viewModel.navigateToDashboard.value = false
            }
        })

        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                navigateToLogin()
                viewModel.navigateToLogin.value = false
            }
        })
    }

    fun startInit(){
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.initSession()
        },1000)
    }

    override fun refresh() {
        // TODO("Not yet implemented")
    }
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    private fun navigateToLogin() {
        activity?.let { LoginActivity.newIntent(it) }?.let { navigateWithFinish(it) }
    }

    private fun navigateToDashboard() {
        navigateWithFinish(DashboardActivity.newIntent(activity))
    }
    private fun navigateWithFinish(intent: Intent) {
        startActivity(intent)
        activity?.finish()
    }
}