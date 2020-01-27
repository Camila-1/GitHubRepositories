package com.example.githubrepositories.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubrepositories.models.RepositoriesDao
import com.example.githubrepositories.models.UsersDao
import com.example.githubrepositories.response.GithubRepositoryOwner
import com.example.githubrepositories.response.UserRepository

@Database(entities = [UserRepository::class, GithubRepositoryOwner::class], version = 1, exportSchema = true)
abstract class GitHubDB: RoomDatabase() {
    abstract fun repositoriesDAO() : RepositoriesDao
    abstract fun usersDAO(): UsersDao
}