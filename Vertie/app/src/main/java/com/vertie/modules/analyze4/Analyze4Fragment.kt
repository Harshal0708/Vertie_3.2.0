package com.vertie.modules.analyze4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vertie.databinding.FragmentAnalyze4Binding
import com.vertie.modules.analyze3.Analyze3Activity
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject

class Analyze4Fragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding : FragmentAnalyze4Binding
    private val TAG = this.javaClass.simpleName

    private val viewModel: Analyze4ViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(Analyze4ViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnalyze4Binding.inflate(inflater,container,false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questions()
        observeViewModel()
        //backButton()
    }

    private fun questions(){
        binding.yescaffaine.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.nevercaffaine, 0.4F)
            ViewCompat.setElevation(binding.nocaffaine, 0.4F)
            //navigate
            viewModel.caffeine()
        }

        binding.nevercaffaine.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.yescaffaine, 0.4F)
            ViewCompat.setElevation(binding.nocaffaine, 0.4F)
            //navigate
            viewModel.caffeine()
        }

        binding.nocaffaine.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.nevercaffaine, 0.4F)
            ViewCompat.setElevation(binding.yescaffaine, 0.4F)
            //navigate
            viewModel.caffeine()
        }

        binding.yesnicotine.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.nevernicotine, 0.4F)
            ViewCompat.setElevation(binding.nonicotine, 0.4F)
            //navigate
            viewModel.nicotine()
        }

        binding.nonicotine.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.nevernicotine, 0.4F)
            ViewCompat.setElevation(binding.yesnicotine, 0.4F)
            //navigate
            viewModel.nicotine()
        }

        binding.nevernicotine.setOnClickListener {
            ViewCompat.setElevation(it, 20.0F)
            ViewCompat.setElevation(binding.yesnicotine, 0.4F)
            ViewCompat.setElevation(binding.nonicotine, 0.4F)
            //navigate
            viewModel.nicotine()
        }
    }

    private fun observeViewModel(){
        viewModel.goToNextCaffeine.observe(viewLifecycleOwner, Observer {
            if(it){
                if(viewModel.goToNextNicotine.value == true){
                    viewModel.goToNextNicotine.value = false
                    viewModel.goToNextCaffeine.value = false
                    Toast.makeText(activity?.applicationContext,"done1",Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.goToNextNicotine.observe(viewLifecycleOwner, Observer {
            if(it){
                if(viewModel.goToNextCaffeine.value == true){
                    viewModel.goToNextNicotine.value = false
                    viewModel.goToNextCaffeine.value = false
                    Toast.makeText(activity?.applicationContext,"done2",Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.back.observe(viewLifecycleOwner, Observer {
            if(it){
                navigateBack()
            }
        })
    }

//    fun backButton(){
//        binding.analyze4backbutton.setOnClickListener {
//            viewModel.back()
//        }
//    }

    private fun navigateBack(){
        navigateWithFinish(Analyze3Activity.newIntent(activity!!.applicationContext))
    }

    private fun navigateWithFinish(intent: Intent) {
        startActivity(intent)
        activity?.finish()
    }

    private fun navigateToAnalyze4(){
        //navigateWithFinish(Analyze4Activity.newIntent(activity!!.applicationContext))
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}