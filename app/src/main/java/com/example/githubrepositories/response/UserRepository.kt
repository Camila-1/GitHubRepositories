package com.example.githubrepositories.response

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "repositories", foreignKeys = [ForeignKey(
    entity = GithubRepositoryOwner::class,
    parentColumns = ["id"],
    childColumns = ["ownerId"],
    onDelete = CASCADE)]
    )
data class UserRepository (
    val name: String,
    val private: Boolean,
    @J
    val ownerId: Int,
    @Ignore
    val owner: GithubRepositoryOwner,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "pushed_at") val pushedAt: String,
    @Json(name = "stargazers_count") val stargazersCount: Int,
    @Json(name = "watchers_count") val watchersCount: Int,
    val language: String?
)