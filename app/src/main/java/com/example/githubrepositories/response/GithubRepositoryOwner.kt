package com.example.githubrepositories.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class GithubRepositoryOwner (
    var login: String = "",
    @PrimaryKey
    var id: Int = 0,
    @Json(name = "avatar_url") var avatarUrl: String = ""
): Parcelable