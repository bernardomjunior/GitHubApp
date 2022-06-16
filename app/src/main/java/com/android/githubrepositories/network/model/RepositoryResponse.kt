package com.android.githubrepositories.network.model

import com.google.gson.annotations.SerializedName

internal data class RepositoryResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("owner")
    val owner: UserResponse
)
