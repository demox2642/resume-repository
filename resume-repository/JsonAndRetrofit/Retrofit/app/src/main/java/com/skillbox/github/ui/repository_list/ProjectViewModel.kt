package com.skillbox.github.ui.repository_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.Project

class ProjectViewModel : ViewModel() {

    private val repository = ProjectRepository()
    private val projectListLiveData = MutableLiveData<List<Project>>(repository.getProjectList())
    private val isLoadingLiveDataProject = MutableLiveData(false)
    private val serverErrorLiveData = MutableLiveData<String>()
    private val starStatusLiveData = MutableLiveData<Boolean>()

    val projectList: LiveData<List<Project>>
        get() = projectListLiveData

    val isLoadingProject: LiveData<Boolean>
        get() = isLoadingLiveDataProject

    val serverError: LiveData<String>
        get() = serverErrorLiveData

    val starStatus: LiveData<Boolean>
        get() = starStatusLiveData

    fun loadProgectList() {

        isLoadingLiveDataProject.postValue(true)
        repository.getRepositoryList(
            onComplite = { progects ->
                Log.e("Progects", "$progects")

                projectListLiveData.postValue(progects)
                isLoadingLiveDataProject.postValue(false)
            },
            onError = { throwable ->
                Log.e("loadingUser user", "$throwable")
                serverErrorLiveData.postValue(throwable.message)
                isLoadingLiveDataProject.postValue(false)
            }
        )
    }

    fun checkStarStatus(
        owner: String,
        repo: String
    ) {

        repository.checkStarStatus(
            owner, repo,
            onComplite = { status ->
                Log.e("checkStarStatus", "$status")
                starStatusLiveData.postValue(status)
            },
            onError = { throwable ->
                Log.e("checkStarStatus", "$throwable")
            }
        )
    }

    fun changeStarStatus(
        owner: String,
        repo: String
    ) {
        if (starStatusLiveData.value!!) {
            repository.deleteStar(
                owner, repo,
                onComplite = { status ->
                    Log.e("deliteStar", "$status")
                    starStatusLiveData.postValue(status)
                },
                onError = { throwable ->
                    Log.e("deliteStar", "$throwable")
                }
            )
        } else {
            repository.addStar(
                owner, repo,
                onComplite = { status ->
                    Log.e("deliteStar", "$status")
                    starStatusLiveData.postValue(status)
                },
                onError = { throwable ->
                    Log.e("deliteStar", "$throwable")
                }
            )
        }
    }
}
