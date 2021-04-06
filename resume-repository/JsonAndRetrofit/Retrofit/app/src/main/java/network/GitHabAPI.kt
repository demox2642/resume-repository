package network


import com.skillbox.github.data.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface GitHabAPI {
    @GET("user")
    fun getUserInformation(): Call<User>

    @GET("user/repos")
    fun getProjectList(): Call<List<Project>>

    @GET("user/starred/{owner}/{repo}")
    fun checkStar(
            @Path("owner") owner: String,
            @Path("repo") repo: String

    ): Call<String>

    @PUT("user/starred/{owner}/{repo}")
    fun addStar(
            @Path("owner") owner: String,
            @Path("repo") repo: String

    ): Call<String>

    @DELETE("user/starred/{owner}/{repo}")
    fun deliteStar(
            @Path("owner") owner: String,
            @Path("repo") repo: String

    ): Call<String>

    @PATCH("user")
    fun updateUserInfo(
            @Body name: UserForChange
    ): Call<User>
}