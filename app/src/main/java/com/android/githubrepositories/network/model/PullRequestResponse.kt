package com.android.githubrepositories.network.model

import com.google.gson.annotations.SerializedName

internal data class PullRequestResponse(
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String?,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("created_at")
    val createdAt: String
)