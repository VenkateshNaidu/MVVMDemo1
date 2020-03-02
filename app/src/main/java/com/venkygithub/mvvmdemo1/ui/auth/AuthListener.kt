package com.venkygithub.mvvmdemo1.ui.auth

import androidx.lifecycle.LiveData
import com.venkygithub.mvvmdemo1.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User/*loginResponse: LiveData<String>*/)
    fun onFailure(message :String)

}