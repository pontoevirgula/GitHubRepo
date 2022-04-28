package com.chslcompany.githubrepo.data.remote.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    private const val URL = "https://api.github.com/"
    private const val maxSecondsToRequest: Long = 30

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()
        .readTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
        .connectTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
        .writeTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
                .addHeader("Content-Type", "application/json")
            chain.proceed(builder.build())
        }.addInterceptor(loggingInterceptor)
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    fun getService(): GithubApi = retrofit.create(GithubApi::class.java)
}
