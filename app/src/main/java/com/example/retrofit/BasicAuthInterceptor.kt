package com.example.retrofit

import android.R.attr
import okhttp3.*
import java.io.IOException


class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic("http", "http")

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}