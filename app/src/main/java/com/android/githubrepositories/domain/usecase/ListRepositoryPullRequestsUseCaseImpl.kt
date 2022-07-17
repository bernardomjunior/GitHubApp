package com.android.githubrepositories.domain.usecase

import com.android.githubrepositories.domain.ResultWrapper
import com.android.githubrepositories.domain.model.PullRequestModel
import com.android.githubrepositories.domain.repository.GithubRepository

internal class ListRepositoryPullRequestsUseCaseImpl(
    private val repository: GithubRepository
): ListRepositoryPullRequestsUseCase {

    override suspend fun execute(
        creator: String,
        repositoryName: String
    ): ResultWrapper<List<PullRequestModel>> {
        return repository.listPullRequests(
            creator = creator,
            repository = repositoryName
        )
    }
}