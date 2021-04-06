package com.skillbox.github.ui.current_user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.User
import com.skillbox.github.data.UserForChange

class UserViewModel : ViewModel() {

    private val repository = UserRepository()
    private val userLiveData = MutableLiveData <User>()
    private val serverErrorLiveData = MutableLiveData<String>()
    private val isLoadingLiveData = MutableLiveData(false)

    val userViewModel: LiveData<User>
        get() = userLiveData

    val serverError: LiveData<String>
        get() = serverErrorLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun loadingUser() {

        isLoadingLiveData.postValue(true)
        Log.e("loadingUser user", "start")
        repository.user(
            onComplite = { user ->
                Log.e("loadingUser user", "$user")
                userLiveData.postValue(user)
                isLoadingLiveData.postValue(false)
            },
            onError = { throwable ->
                Log.e("loadingUser user", "$throwable")
                serverErrorLiveData.postValue(throwable.message)
                isLoadingLiveData.postValue(false)
            }
        )

        Log.e("loadingUser user", "end")
    }

    fun changeUser(
        newUser: UserForChange
    ) {
        repository.updateUserInfo(
            newUser,
            onComplite = { user ->
                Log.e("loadingUser user", "$user")
                userLiveData.postValue(user)
                isLoadingLiveData.postValue(false)
            },
            onError = { throwable ->
                Log.e("loadingUser user", "$throwable")
                serverErrorLiveData.postValue(throwable.message)
                isLoadingLiveData.postValue(false)
            }
        )
    }
}
