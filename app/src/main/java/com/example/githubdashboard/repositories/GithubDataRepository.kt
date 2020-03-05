package com.example.githubdashboard.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubdashboard.web.generators.ServiceGenerator
import com.example.githubdashboard.web.data.SearchData
import com.example.githubdashboard.web.networkinterfaces.GithubApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubDataRepository {


    private val githubApi: GithubApiInterface = ServiceGenerator.createService(GithubApiInterface::class.java)

    val searchResults = MutableLiveData<SearchData>()

    fun searchUser(data: String) {
        val keywords: String = data.replace(' ', '+')
        val searchCall: Call<SearchData> = githubApi.searchUser(keywords)

        searchCall.enqueue(object: Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                Log.i("TEST", "Response: $response")
                if (!response.isSuccessful)
                    return
                searchResults.postValue(response.body())
                Log.i("TEST", "Response body: ${response.body().toString()}")
            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}