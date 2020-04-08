package com.venkygithub.mvvmdemo1.ui.home.profile

import androidx.lifecycle.ViewModel
import com.venkygithub.mvvmdemo1.data.repository.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

    val user = repository.getUser()

}
