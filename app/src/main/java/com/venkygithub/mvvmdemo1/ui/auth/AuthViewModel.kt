package com.venkygithub.mvvmdemo1.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.venkygithub.mvvmdemo1.data.repository.UserRepository
import com.venkygithub.mvvmdemo1.util.ApiExceptions
import com.venkygithub.mvvmdemo1.util.Coroutines
import com.venkygithub.mvvmdemo1.util.NoInternetException

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null


    var authListener : AuthListener? =null

    fun getLoggedInUser() =userRepository.getUser()

    fun onLoginButtonClicked(view: View) {

        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid credentials")
            return
        }

        Coroutines.main {

            try {
                val authResponse = userRepository.userLoginn(email!!,password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    userRepository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e :ApiExceptions){
                authListener?.onFailure(e.message!!)
            }catch (e :NoInternetException){
                authListener?.onFailure(e.message!!)
            }

            /*val response = UserRepository().userLoginn(email!!,password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }*/

        }

       /* val loginResponse = UserRepository().userLoginn(email!!,password!!)
        authListener?.onSuccess(loginResponse)*/
    }

    fun onSignUp(view : View){
        Intent(view.context,SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view : View){
        Intent(view.context,
            LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onSignUpButtonClicked(view: View) {

        authListener?.onStarted()

        if(name.isNullOrEmpty()){
            authListener?.onFailure(" Name required")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.onFailure("email required")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("password required")
            return
        }

        if (password != passwordConfirm) {
            authListener?.onFailure("password not matched")
            return
        }

        Coroutines.main {

            try {
                val authResponse = userRepository.userSignUpp(name!!,email!!,password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    userRepository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e :ApiExceptions){
                authListener?.onFailure(e.message!!)
            }catch (e :NoInternetException){
                authListener?.onFailure(e.message!!)
            }

            /*val response = UserRepository().userLoginn(email!!,password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }*/

        }

        /* val loginResponse = UserRepository().userLoginn(email!!,password!!)
         authListener?.onSuccess(loginResponse)*/
    }

}