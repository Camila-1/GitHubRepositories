package com.example.githubrepositories.repositories

import android.content.Context
import androidx.room.Room
import com.example.githubrepositories.db.GitHubDB
import com.example.githubrepositories.response.GithubRepositoryOwner
import com.example.githubrepositories.response.UserRepository
import com.example.githubrepositories.services.githubrepositoriesservice.GitHubAPIRequest
import kotlin.math.log

class GitHubRepository(val context: Context) {
    private val db = Room.databaseBuilder(context, GitHubDB::class.java, "github_database").build()
    private val repositoriesDAO = db.repositoriesDAO()
    private val usersDAO = db.usersDAO()

    suspend fun getUserByLogin(login: String): GithubRepositoryOwner? {
        val test = usersDAO.getUser(login)
        return usersDAO.getUser(login)
    }

    suspend fun repositoriesList(login: String) : List<UserRepository> {
        val repositories =  loadRepositories(login)
        val userId = usersDAO.insert(repositories?.get(0)?.owner)
        repositoriesDAO.insert(repositories)

        return repositoriesByUserId(userId)
    }

    private suspend fun loadRepositories(login: String): List<UserRepository>? = GitHubAPIRequest(context).repositories(login)

    private suspend fun repositoriesByUserId(id: Long): List<UserRepository> {
        return repositoriesDAO.repositories(id)
    }
}