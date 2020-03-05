package com.example.githubdashboard.web.interceptors

import com.example.githubdashboard.web.generators.ServiceGenerator.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder().header("Authorization", AUTH_TOKEN)
        val request: Request = builder.build()
        return chain.proceed(request)
    }

}