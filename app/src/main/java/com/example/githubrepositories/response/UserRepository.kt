package com.example.githubrepositories.response

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(indices = [Index("owner_id")],
    tableName = "repositories",
    foreignKeys = [ForeignKey(
    entity = GithubRepositoryOwner::class,
    parentColumns = ["id"],
    childColumns = ["owner_id"],
    onDelete = CASCADE)]
    )
data class UserRepository (
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var private: Boolean = false,
    @Transient @ColumnInfo(name = "owner_id") var ownerId: Int = 0,
    @Ignore var owner: GithubRepositoryOwner? = null,
    @Json(name = "created_at") var createdAt: String = "",
    @Json(name = "updated_at") var updatedAt: String = "",
    @Json(name = "pushed_at") var pushedAt: String = "",
    @Json(name = "stargazers_count") var stargazersCount: Int = 0,
    @Json(name = "forks_count") var forksCount: Int = 0,
    @Json(name = "watchers_count") var watchersCount: Int = 0,
    var language: String? = ""
): Parcelable {
    init {
        ownerId = owner?.id ?: 0
    }
}