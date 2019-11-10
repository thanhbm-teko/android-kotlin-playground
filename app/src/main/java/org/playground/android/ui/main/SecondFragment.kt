package org.playground.android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.playground.android.BR
import org.playground.android.R
import org.playground.android.databinding.MainFragmentBinding
import org.playground.android.databinding.SecondFragmentBinding
import timber.log.Timber

class SecondFragment : Fragment(), NavigationView {

    private lateinit var viewModel: SecondViewModel
    private lateinit var viewDataBinding: SecondFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.second_fragment, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        viewModel.setView(this)
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()

        initComponents()
    }

    private fun initComponents() {
        viewModel.getStringDelayedLiveData().observe(viewLifecycleOwner, Observer {
            Timber.d(it)
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onNext() {
        findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
    }

    override fun onBack() {
        findNavController().navigateUp()
    }

}
