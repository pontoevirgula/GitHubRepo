package com.chslcompany.githubrepo.core.di

import com.chslcompany.githubrepo.BuildConfig
import com.chslcompany.githubrepo.data.remote.api.GithubApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    fun providerGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    single { providerGithubApi(get()) }
}

val networkModule = module {

    fun providerOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(60L, TimeUnit.SECONDS)
            readTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request = chain.request()
                    val builder = request.newBuilder()
                        .addHeader("Content-Type", "application/json")
                    chain.proceed(builder.build())
                }.addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
        }.build()

    fun providerRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(BuildConfig.API)
            client(client)
            addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
        }.build()

    single { providerRetrofit(providerOkHttpClient()) }
}