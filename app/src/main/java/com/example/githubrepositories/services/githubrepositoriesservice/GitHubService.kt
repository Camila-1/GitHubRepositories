package com.example.githubrepositories.services.githubrepositoriesservice

import retrofit2.Call
import com.example.githubrepositories.response.UserRepository
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{username}/repos")
    fun repositoriesByUserName(@Path("username") userName: String): Call<List<UserRepository>>
}