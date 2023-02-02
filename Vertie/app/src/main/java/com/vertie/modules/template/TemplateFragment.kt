package com.vertie.modules.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vertie.modules.base.BaseFragment
import javax.inject.Inject
import com.vertie.modules.base.BaseViewModel
import com.vertie.databinding.FragmentTemplateBinding


class TemplateFragment @Inject constructor() : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentTemplateBinding
    private val TAG = this.javaClass.simpleName

    private val viewModel: TemplateViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TemplateViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTemplateBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun refresh() {
        // TODO("Not yet implemented")
    }
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}