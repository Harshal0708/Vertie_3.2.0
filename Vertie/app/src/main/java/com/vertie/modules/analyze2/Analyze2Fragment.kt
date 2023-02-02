package com.vertie.modules.analyze2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vertie.databinding.FragmentAnalyze2Binding
import com.vertie.modules.analyze1.Analyze1Activity
import com.vertie.modules.analyze3.Analyze3Activity
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject

class Analyze2Fragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentAnalyze2Binding
    private val TAG = this.javaClass.simpleName

    private val viewModel: Analyze2ViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(Analyze2ViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnalyze2Binding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fills()
        observeViewModel()
        //backButton()
    }

    private fun observeViewModel(){
        viewModel.goToAnalyze3.observe(viewLifecycleOwner, Observer {
            if(it){
                viewModel.goToAnalyze3.value = false
                navigateToAnalyze3()
            }
        })

        viewModel.back.observe(viewLifecycleOwner, Observer {
            if(it){
                navigateBack()
            }
        })
    }

//    fun backButton(){
//        binding.analyze2backbutton.setOnClickListener {
//            viewModel.back()
//        }
//    }

    private fun navigateBack(){
        navigateWithFinish(Analyze1Activity.newIntent(activity!!.applicationContext))
    }

    private fun fills(){
        binding.fillread.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.fillwork.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.fillholiday.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.fillphysicallabor.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.fillmeditation.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.fillsleep.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.filllearn.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.fillexercise.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
        binding.fillwalk.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            //go to next analysis
            viewModel.nextAnalysis()
        }
    }

    private fun navigateWithFinish(intent: Intent) {
        startActivity(intent)
        //activity?.finish()
    }

    private fun navigateToAnalyze3(){
        activity?.let { Analyze3Activity.newIntent(it.applicationContext) }
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
        ViewCompat.setElevation(binding.fillread, 0.6F)
        ViewCompat.setElevation(binding.fillwork, 0.6F)
        ViewCompat.setElevation(binding.fillexercise, 0.6F)
        ViewCompat.setElevation(binding.filllearn, 0.6F)
        ViewCompat.setElevation(binding.fillholiday, 0.6F)
        ViewCompat.setElevation(binding.fillmeditation, 0.6F)
        ViewCompat.setElevation(binding.fillphysicallabor, 0.6F)
        ViewCompat.setElevation(binding.fillwalk, 0.6F)
        ViewCompat.setElevation(binding.fillsleep, 0.6F)
    }
}