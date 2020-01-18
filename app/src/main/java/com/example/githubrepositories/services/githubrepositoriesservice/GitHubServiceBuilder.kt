package com.example.githubrepositories.services.githubrepositoriesservice

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GitHubServiceBuilder(val context: Context) {
    private val BASE_URL = "https://api.github.com/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .protocols(listOf(Protocol.HTTP_1_1))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun gitHubService() = retrofit.create(GitHubService::class.java)
}