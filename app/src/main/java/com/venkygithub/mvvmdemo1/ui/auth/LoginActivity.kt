package com.venkygithub.mvvmdemo1.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.venkygithub.mvvmdemo1.R
import com.venkygithub.mvvmdemo1.data.db.entities.User
import com.venkygithub.mvvmdemo1.databinding.ActivityLoginBinding
import com.venkygithub.mvvmdemo1.ui.home.profile.ProfileViewModelFactory
import com.venkygithub.mvvmdemo1.util.hide
import com.venkygithub.mvvmdemo1.util.show
import com.venkygithub.mvvmdemo1.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(),
    AuthListener,KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

     /*   val networkConnectionIntercepter =NetworkConnectionIntercepter(this)
        val appDatabase = AppDatabase(this)
        val myApi = MyApi(networkConnectionIntercepter)
        val userRepository = UserRepository(myApi,appDatabase)
        val factory = AuthViewModelFactory(userRepository)*/



        val binding:ActivityLoginBinding =DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewmodel =ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewmodel

        viewmodel.authListener = this

        viewmodel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
        )

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User/*loginResponse: LiveData<String>*/) {
        /*loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })*/
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
        //toast("${user.name} is Logged In")

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        //toast(message)
        root_layout.snackbar(message)

    }



}
