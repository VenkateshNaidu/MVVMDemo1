package com.venkygithub.mvvmdemo1.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.venkygithub.mvvmdemo1.data.repository.UserRepository
import com.venkygithub.mvvmdemo1.util.Coroutines

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

     var authListener : AuthListener? =null

    fun onLoginButtonClicked(view: View) {

        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid credentials")
            return
        }

        Coroutines.main {
            val response = UserRepository().userLoginn(email!!,password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }

        }

       /* val loginResponse = UserRepository().userLoginn(email!!,password!!)
        authListener?.onSuccess(loginResponse)*/
    }

}