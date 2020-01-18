package com.example.githubrepositories.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.repositories.GitHubRepository

@Suppress("UNCHECKED_CAST")
class GitHubViewModelFactory(private val repository: GitHubRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GitHubViewModel(repository) as T
    }
}