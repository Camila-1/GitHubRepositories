package com.example.githubrepositories.services.githubrepositoriesservice

import android.content.Context
import com.example.githubrepositories.response.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubAPIRequest(context: Context) {
    private val serviceBuilder = GitHubServiceBuilder(context)

    suspend fun repositories(): List<UserRepository>? {
        return withContext(Dispatchers.IO) {
            serviceBuilder.gitHubService().repositoriesByUserName("Camila-1").execute().body()
        }
    }
}