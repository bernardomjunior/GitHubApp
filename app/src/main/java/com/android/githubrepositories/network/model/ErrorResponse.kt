package com.android.githubrepositories.network.model

import com.google.gson.annotations.SerializedName

internal data class ErrorResponse(
    @SerializedName("message")
    val message: String?
)