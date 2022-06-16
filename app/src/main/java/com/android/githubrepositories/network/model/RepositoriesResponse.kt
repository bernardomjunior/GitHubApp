package com.android.githubrepositories.network.model

import com.google.gson.annotations.SerializedName

internal data class RepositoriesResponse(
    @SerializedName("items")
    val items: List<RepositoryResponse>
)




