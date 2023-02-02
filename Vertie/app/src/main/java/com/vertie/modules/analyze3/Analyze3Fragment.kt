package com.vertie.modules.analyze3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vertie.R
import com.vertie.databinding.FragmentAnalyze3Binding
import com.vertie.modules.analyze2.Analyze2Activity
import com.vertie.modules.analyze4.Analyze4Activity
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject

class Analyze3Fragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentAnalyze3Binding
    private val TAG = this.javaClass.simpleName

    private val viewModel: Analyze3ViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(Analyze3ViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnalyze3Binding.inflate(inflater,container,false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feelingButton()
        moods()
        observeViewModel()
        //backButton()
    }

    private fun moods(){
        binding.tensemood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.sickmood, 0.4F)
            ViewCompat.setElevation(binding.fantastaticmood, 0.4F)
            ViewCompat.setElevation(binding.tiredmood, 0.4F)
            binding.feelingButton.isEnabled = true
            binding.feelingButton.background = resources.getDrawable(R.drawable.bk_rounded_2c72f9,null)
        }

        binding.tiredmood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.sickmood, 0.4F)
            ViewCompat.setElevation(binding.fantastaticmood, 0.4F)
            ViewCompat.setElevation(binding.tensemood, 0.4F)
            binding.feelingButton.isEnabled = true
            binding.feelingButton.background = resources.getDrawable(R.drawable.bk_rounded_2c72f9,null)
        }

        binding.fantastaticmood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.sickmood, 0.4F)
            ViewCompat.setElevation(binding.tensemood, 0.4F)
            ViewCompat.setElevation(binding.tiredmood, 0.4F)
            binding.feelingButton.isEnabled = true
            binding.feelingButton.background = resources.getDrawable(R.drawable.bk_rounded_2c72f9,null)
        }

        binding.sickmood.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.tensemood, 0.4F)
            ViewCompat.setElevation(binding.fantastaticmood, 0.4F)
            ViewCompat.setElevation(binding.tiredmood, 0.4F)
            binding.feelingButton.isEnabled = true
            binding.feelingButton.background = resources.getDrawable(R.drawable.bk_rounded_2c72f9,null)
        }
    }

    private fun feelingButton(){
        binding.feelingButton.setOnClickListener{
            viewModel.nextAnalysis()
        }
    }

//    fun backButton(){
//        binding.analyze3backbutton.setOnClickListener {
//            viewModel.back()
//        }
//    }

    private fun navigateBack(){
        navigateWithFinish(Analyze2Activity.newIntent(activity!!.applicationContext))
    }

    private fun observeViewModel(){
        viewModel.goToAnalyze4.observe(viewLifecycleOwner, Observer {
            if(it){
                viewModel.goToAnalyze4.value =false
                navigateToAnalyze4()
            }
        })

        viewModel.back.observe(viewLifecycleOwner, Observer {
            if(it){
                navigateBack()
            }
        })
    }

    private fun navigateWithFinish(intent: Intent) {
        startActivity(intent)
        //activity?.finish()
    }

    private fun navigateToAnalyze4(){
        activity?.let { Analyze4Activity.newIntent(it.applicationContext) }
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
        ViewCompat.setElevation(binding.tensemood, 0.6F)
        ViewCompat.setElevation(binding.fantastaticmood, 0.6F)
        ViewCompat.setElevation(binding.tiredmood, 0.6F)
        ViewCompat.setElevation(binding.sickmood,0.6F)
    }
}