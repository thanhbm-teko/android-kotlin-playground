package org.playground.android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.playground.android.BR
import org.playground.android.R
import org.playground.android.databinding.MainFragmentBinding

class MainFragment : Fragment(), NavigationView {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewDataBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setView(this)
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }

    override fun onNext() {
        findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
    }

    override fun onBack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
