package com.example.githubrepositories.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepositories.response.GithubRepositoryOwner

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: GithubRepositoryOwner?): Long

    @Query("SELECT * FROM users WHERE login == :login")
    suspend fun getUser(login: String): GithubRepositoryOwner?
}