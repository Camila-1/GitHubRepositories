package com.example.githubrepositories.viewmodels

import androidx.lifecycle.ViewModel
import com.example.githubrepositories.repositories.GitHubRepository
import com.example.githubrepositories.response.UserRepository

class GitHubViewModel(private val repository: GitHubRepository): ViewModel() {

    suspend fun getRepositories(login: String): List<UserRepository>? {
        return repository.repositoriesList(login)
    }
}