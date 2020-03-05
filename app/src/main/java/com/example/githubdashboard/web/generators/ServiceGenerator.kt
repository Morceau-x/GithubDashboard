package com.example.githubdashboard.web.generators

import com.example.githubdashboard.BuildConfig
import com.example.githubdashboard.web.interceptors.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {
    private const val API_BASE_URL = "https://api.github.com"
    const val REDIRECT_URI = "githubdashboard://authorized"
    val AUTH_TOKEN = "token " + BuildConfig.TOKEN
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())


    fun <S> createService(serviceClass: Class<S>): S {
        val interceptor = AuthenticationInterceptor()
        if (!httpClient.interceptors().contains(interceptor))
            httpClient.addInterceptor(interceptor)
        builder.client(httpClient.build())
        return builder.build().create(serviceClass)
    }
}