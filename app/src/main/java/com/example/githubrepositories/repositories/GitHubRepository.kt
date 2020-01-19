package com.example.githubrepositories.repositories

import android.content.Context
import com.example.githubrepositories.response.UserRepository
import com.example.githubrepositories.services.githubrepositoriesservice.GitHubAPIRequest

class GitHubRepository(val context: Context) {

    suspend fun repositoriesList(login: String) : List<UserRepository>? {
        return GitHubAPIRequest(context).repositories(login)
    }
}