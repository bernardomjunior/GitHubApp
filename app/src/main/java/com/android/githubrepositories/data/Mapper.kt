package com.android.githubrepositories.data

import com.android.githubrepositories.domain.model.PullRequestModel
import com.android.githubrepositories.domain.model.RepositoriesModel
import com.android.githubrepositories.domain.model.RepositoryModel
import com.android.githubrepositories.domain.model.UserModel
import com.android.githubrepositories.network.model.PullRequestResponse
import com.android.githubrepositories.network.model.RepositoriesResponse
import com.android.githubrepositories.network.model.RepositoryResponse
import com.android.githubrepositories.network.model.UserResponse
import java.text.SimpleDateFormat
import java.util.*

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

internal fun List<PullRequestResponse>.toModel(): List<PullRequestModel> {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    return this.map {
        PullRequestModel(
            user = it.user.toModel(),
            title = it.title,
            body = it.body,
            htmlUrl = it.htmlUrl,
            createdAt = formatter.parse(it.createdAt)!!
        )
    }
}

