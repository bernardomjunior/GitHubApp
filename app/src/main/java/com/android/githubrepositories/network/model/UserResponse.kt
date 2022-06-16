package com.android.githubrepositories.network.model

import com.google.gson.annotations.SerializedName

internal data class UserResponse(
    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("login")
    val nickName: String
)
