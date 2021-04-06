package com.skillbox.github.ui.repository_list

import android.util.Log
import com.skillbox.github.data.Project
import network.NetworkRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class ProjectRepository {
    private var projectList: List<Project> = listOf()

    fun getProjectList(): List<Project> {
        return projectList
    }

    fun getRepositoryList(
        onComplite: (List<Project>) -> Unit,

        onError: (Throwable) -> Unit
    ) {
        NetworkRetrofit.guitHabAPI.getProjectList().enqueue(
            object : Callback<List<Project>> {

                override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<List<Project>>,
                    response: Response<List<Project>>
                ) {
                    if (response.isSuccessful) {
                        onComplite(response.body().orEmpty())
                    } else {
                        onError(RuntimeException("Пошло что то не так"))
                    }
                }
            }
        )
    }

    fun checkStarStatus(
        owner: String,
        repo: String,
        onComplite: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        NetworkRetrofit.guitHabAPI.checkStar(owner, repo).enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.e("chechStarStatus", "${response.code()}")
                    when (response.code()) {
                        204 -> onComplite(true)
                        404 -> onComplite(false)
                        else -> onError(RuntimeException("Warning!!Error: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }

    fun addStar(
        owner: String,
        repo: String,
        onComplite: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        NetworkRetrofit.guitHabAPI.addStar(owner, repo).enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.e("addStar", "${response.code()}")
                    when (response.code()) {
                        204 -> onComplite(true)
                        404 -> onComplite(false)
                        else -> onError(RuntimeException("Warning!!Error: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }

    fun deleteStar(
        owner: String,
        repo: String,
        onComplite: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        NetworkRetrofit.guitHabAPI.deliteStar(owner, repo).enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.e("addStar", "${response.code()}")
                    when (response.code()) {
                        204 -> onComplite(false)
                        404 -> onComplite(true)
                        else -> onError(RuntimeException("Warning!!Error: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}
