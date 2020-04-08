package com.venkygithub.mvvmdemo1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.venkygithub.mvvmdemo1.data.db.AppDatabase
import com.venkygithub.mvvmdemo1.data.db.entities.User
import com.venkygithub.mvvmdemo1.data.network.MyApi
import com.venkygithub.mvvmdemo1.data.network.SafeApiRequest
import com.venkygithub.mvvmdemo1.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val api : MyApi,
private val db :AppDatabase) :SafeApiRequest(){

    suspend fun userLoginn(email :String,password :String) : AuthResponse/*Response<AuthResponse>*//*LiveData<String>*/ {

        return apiRequest { api.userLogin(email,password)  }

        //return MyApi().userLogin(email,password)

        /*val loginResponse = MutableLiveData<String>()

        MyApi().userLogin(email,password)
            .enqueue(object : Callback<ResponseBody>{

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    }else{
                        loginResponse.value = response.errorBody()?.string()
                    }
                }
            })

            return loginResponse*/
    }

    suspend fun userSignUpp( name :String,email :String , password : String) : AuthResponse{

        return apiRequest { api.userSignUp(name,email,password) }
    }

    suspend fun saveUser(user : User) = db.getUserDao().upsert(user)

    fun getUser() =db.getUserDao().getUser()

}