package com.venkygithub.mvvmdemo1.data.network.responses

import com.venkygithub.mvvmdemo1.data.db.entities.User

data class AuthResponse (
    val isSuccessful :Boolean?,
    val message : String?,
    val user: User?
)