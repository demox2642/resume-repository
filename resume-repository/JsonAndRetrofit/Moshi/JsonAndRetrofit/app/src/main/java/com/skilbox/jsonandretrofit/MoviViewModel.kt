package com.skilbox.networking

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skilbox.networking.data.MovieRepository
import com.skilbox.networking.data.RemoteMovie
import okhttp3.Call

class MoviViewModel : ViewModel() {
    private val repository = MovieRepository()

    private var currentCall: Call? = null

    private val movieListLiveData = MutableLiveData<List<RemoteMovie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val serverErrorLiveData = MutableLiveData<String>()
    private val likeIsAddLiveData = MutableLiveData<Boolean>()

    val movieList: LiveData<List<RemoteMovie>>
        get() = movieListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val serverError: LiveData<String>
        get() = serverErrorLiveData

    val likeIsAdd: LiveData<Boolean>
        get() = likeIsAddLiveData

    fun search(text: String, year: String?, type: String?) {
        isLoadingLiveData.postValue(true)
        repository.searchMovie(text, year, type) { movies ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movies)
            serverErrorLiveData.postValue(repository.getErrorMassage())
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }

    fun createScore(position: Int) {
        Log.e("createScore", "$position")
        repository.addMyScories(position)
        likeIsAddLiveData.postValue(repository.getMyScories())
    }
}
