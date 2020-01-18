package com.example.githubrepositories.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepositories.response.UserRepository

@Dao
interface RepositoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<UserRepository>)

    @Query("SELECT * FROM repositories WHERE ownerId = :ownerId")
    suspend fun repositories(ownerId: Int): List<UserRepository>
}