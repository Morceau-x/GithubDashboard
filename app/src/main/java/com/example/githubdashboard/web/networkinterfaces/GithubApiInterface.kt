package com.example.githubdashboard.web.networkinterfaces

import com.example.githubdashboard.web.data.SearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiInterface {
    @GET("/search/users")
    fun searchUser(
        @Query("q") keywords: String
    ): Call<SearchData>
}