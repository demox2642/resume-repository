package com.skillbox.github.ui.current_user

import android.util.Log
import com.skillbox.github.data.User
import com.skillbox.github.data.UserForChange
import network.NetworkRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class UserRepository {

    fun user(
        onComplite: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        NetworkRetrofit.guitHabAPI.getUserInformation().enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.e("user", "${response.body()}")
                    if (response.isSuccessful) {
                        onComplite(response.body()!!)
                    } else {
                        onError(RuntimeException("Что то не то"))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }

    fun updateUserInfo(
        user: UserForChange,
        onComplite: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        NetworkRetrofit.guitHabAPI.updateUserInfo(user).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.e("user", "${response.body()}")
                    if (response.isSuccessful) {
                        onComplite(response.body()!!)
                    } else {
                        onError(RuntimeException("Что то не то"))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}
