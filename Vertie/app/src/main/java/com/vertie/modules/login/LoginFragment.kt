package com.vertie.modules.login

//import com.vertie.AnswerActivity

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.vertie.Constants
import com.vertie.databinding.FragmentLoginBinding
import com.vertie.javacode.activities.ChildSelectionActivity
import com.vertie.javacode.activities.ForgotPassword
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import com.vertie.modules.dashboard.DashboardActivity
import javax.inject.Inject


class LoginFragment @Inject constructor() : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentLoginBinding
    private val TAG = this.javaClass.simpleName

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
//        validationAfterEdit()
    }

//    private fun validationAfterEdit(){
//        binding.emailLogin.editText.doAfterTextChanged {
//            viewModel.validateEmail()
//        }
//        binding.passwordLogin.editText.doAfterTextChanged{
//            viewModel.validatePassword()
//        }
//    }

    private fun observeViewModel(){
        viewModel.goToDashBoard.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                viewModel.goToDashBoard.value = false
                navigateToDashboard()
            }
        })

        binding.forgetPasswordButton.setOnClickListener {
            activity?.let{
                val intent = Intent (it, ForgotPassword::class.java)
                it.startActivity(intent)
            }
        }

        viewModel.goToChieldActivity.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                activity?.let{ it ->
//                    var sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
                    val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences(Constants.userList, MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(Constants.proxyUserList, Gson().toJson(viewModel.proxyUsers))
                    editor.apply();
//                    val intent = Intent (it, ChildSelectionActivity::class.java)
//                    it.startActivity(intent)
//                    activity!!.finish()
                    activity?.let {
                        navigateToDashboard()
                        activity!!.finish()
                    }
                }
            }else{
                val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences(Constants.userList, MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(Constants.proxyUserList, "null")
                editor.apply();
                viewModel.goToDashBoard.value = false
                navigateToDashboard()
            }
        })

        viewModel.errorLogin.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(activity?.applicationContext,it,Toast.LENGTH_LONG).show()
        })
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    private fun navigateToDashboard() {
        navigateWithFinish(DashboardActivity.newIntent(activity))
    }

    private fun navigateWithFinish(intent: Intent) {
        startActivity(intent)
        activity?.finish()
    }

}