package com.venkygithub.mvvmdemo1.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.venkygithub.mvvmdemo1.R
import com.venkygithub.mvvmdemo1.databinding.ActivityLoginBinding
import com.venkygithub.mvvmdemo1.util.hide
import com.venkygithub.mvvmdemo1.util.show
import com.venkygithub.mvvmdemo1.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        val binding:ActivityLoginBinding =DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewmodel =ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewmodel

        viewmodel.authListener = this

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message )
    }

}
