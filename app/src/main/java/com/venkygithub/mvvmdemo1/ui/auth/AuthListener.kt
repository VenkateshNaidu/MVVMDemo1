package com.venkygithub.mvvmdemo1.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {

    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message :String)

}