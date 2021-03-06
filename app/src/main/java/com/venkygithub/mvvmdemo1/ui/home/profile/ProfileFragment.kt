package com.venkygithub.mvvmdemo1.ui.home.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.venkygithub.mvvmdemo1.R
import com.venkygithub.mvvmdemo1.databinding.ProfileFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein

class ProfileFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: ProfileViewModel
    private val factory : ProfileViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : ProfileFragmentBinding =
            DataBindingUtil.inflate(inflater,R.layout.profile_fragment,container,false)
        viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }



}
