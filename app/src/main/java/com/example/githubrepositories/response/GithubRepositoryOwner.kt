package com.example.githubrepositories.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "users")
data class GithubRepositoryOwner (
    val login: String,
    @PrimaryKey
    val id: Int,
    @Json(name = "avatar_url") val avatarUrl: String
)