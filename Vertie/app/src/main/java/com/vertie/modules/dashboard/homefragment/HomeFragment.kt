package com.vertie.modules.dashboard.homefragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vertie.databinding.FragmentHomeBinding
import com.vertie.javacode.activities.MeasurementIntroActivity
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import de.kenkou.sdk.headless.domain.model.OnboardingQuestionnaireException
import de.kenkou.sdk.headless.domain.model.anamnesis.obqa.OnboardingQuestionnaireAnswersModel
import de.kenkou.sdk.visual.KenkouSDKVisual
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentHomeBinding
    private val TAG = this.javaClass.simpleName

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    //Staring kenkou tutorial
    private fun startTutorial(){
          KenkouSDKVisual.presentMeasurementInstructions(requireActivity())
//        KenkouSDKVisual.presentBiofeedbackIntroduction(requireActivity())
    }

    //Starting kenkou measurement
    private fun startFullMeasurement() {
        try {
            activity?.let {
                OnboardingQuestionnaireAnswersModel(1994,1,127.00,69.00, System.currentTimeMillis(),12.12)
                KenkouSDKVisual.presentMeasurement(it)
            }
        } catch (e: OnboardingQuestionnaireException) {
            showQuestionnaireErrorPopup()
        }
    }

    //Starting questioning
    private fun showQuestionnaireErrorPopup() {
        AlertDialog.Builder(requireContext())
            .setTitle("Measurement Stopped with error")
            .setMessage("Onboarding questionnaire hasn't been completed.")
            .setPositiveButton("Open questionnaire") { dialog, which ->
//                OnboardingQuestionnaireAnswersModel(1994,1,127.00,69.00, System.currentTimeMillis(),12.12)
                KenkouSDKVisual.presentOnboardingQuestionnaire(requireActivity())
            }
            .setNegativeButton("Close") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.setProgress(50)
        clickListeners()
        observe()
    }

    private fun clickListeners(){
        binding.measurementstartbutton.setOnClickListener {
            viewModel.measure()
        }
        binding.measurementTutorial.setOnClickListener {
//            viewModel.learn()
            activity?.let{
                val intent = Intent (it, MeasurementIntroActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    private fun observe(){
        viewModel.goToMeasurement.observe(viewLifecycleOwner){
            if(it){
                startFullMeasurement()
                viewModel.goToMeasurement.value = false
            }
        }

        viewModel.goToTutorial.observe(viewLifecycleOwner){
            if(it){
                startTutorial()
                viewModel.goToTutorial.value = false
            }
        }

    }



    override fun onResume() {
        super.onResume()
        binding.progressBar.setProgress(0)
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}