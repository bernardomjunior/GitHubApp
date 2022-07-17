package com.android.githubrepositories.domain.usecase

import com.android.githubrepositories.domain.ResultWrapper
import com.android.githubrepositories.domain.model.PullRequestModel

internal interface ListRepositoryPullRequestsUseCase {

    suspend fun execute(creator: String, repositoryName: String): ResultWrapper<List<PullRequestModel>>

}