package com.venkygithub.mvvmdemo1.ui.auth

interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message :String)

}