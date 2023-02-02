package com.vertie.modules.analyze1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vertie.databinding.FragmentAnalyze1Binding
import com.vertie.modules.analyze2.Analyze2Activity
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject

class Analyze1Fragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentAnalyze1Binding
    private val TAG = this.javaClass.simpleName

    private val viewModel: Analyze1ViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(Analyze1ViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnalyze1Binding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moods()
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.goToAnalyze2.observe(viewLifecycleOwner, Observer {
            if(it){
                viewModel.goToAnalyze2.value = false
                navigateToAnalyze2()
            }
        })
        viewModel.mood.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()){
                viewModel.saveMood()
            }
        })
    }


    private fun moods(){
        binding.happymood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //var tv : TextView = it as TextView
            //it.setShadowLayer(20f, (-1.0).toFloat(), 1F, Color.LTGRAY);
            //go to next
            viewModel.setMood("Happy")


        }

        binding.badmood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //var tv : TextView = it as TextView
            //it.setShadowLayer(20f, (-1.0).toFloat(), 1F, Color.LTGRAY);
            //go to next
            viewModel.setMood("Bad")
        }

        binding.goodmood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //var tv : TextView = it as TextView
            //it.setShadowLayer(20f, (-1.0).toFloat(), 1F, Color.LTGRAY);
            //go to next
            viewModel.setMood("Good")
        }

        binding.okmood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //var tv : TextView = it as TextView
            //it.setShadowLayer(20f, (-1.0).toFloat(), 1F, Color.LTGRAY);
            //go to next
            viewModel.setMood("Ok")
        }
    }

    private fun navigateWithFinish(intent: Intent) {
        startActivity(intent)
        //activity?.finish()
    }

    private fun navigateToAnalyze2(){
        activity?.let { Analyze2Activity.newIntent(it.applicationContext) }
            ?.let { navigateWithFinish(it) }
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): BaseViewModel {
       return viewModel
    }

    override fun onResume() {
        super.onResume()
        ViewCompat.setElevation(binding.badmood, 0.6F)
        ViewCompat.setElevation(binding.happymood, 0.6F)
        ViewCompat.setElevation(binding.okmood, 0.6F)
        ViewCompat.setElevation(binding.goodmood, 0.6F)
    }
}