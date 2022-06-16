package com.android.githubrepositories.network

import com.android.githubrepositories.domain.model.RepositoriesModel
import com.android.githubrepositories.domain.model.RepositoryModel
import com.android.githubrepositories.domain.model.UserModel
import com.android.githubrepositories.network.model.RepositoriesResponse
import com.android.githubrepositories.network.model.RepositoryResponse
import com.android.githubrepositories.network.model.UserResponse

internal fun RepositoriesResponse.toModel() =
    RepositoriesModel(
        items = items.map { it.toModel() }
    )

internal fun RepositoryResponse.toModel() =
    RepositoryModel(
        name = name,
        description = description ?: "",
        starCount = starCount,
        forksCount = forksCount,
        owner = owner.toModel()
    )

internal fun UserResponse.toModel() =
    UserModel(
        avatar = avatar,
        nickName = nickName
    )