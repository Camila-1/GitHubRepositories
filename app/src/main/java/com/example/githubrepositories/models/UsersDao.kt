package com.example.githubrepositories.models

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.githubrepositories.response.GithubRepositoryOwner

interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: GithubRepositoryOwner)
}