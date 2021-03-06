package com.android.githubrepositories.domain.repository

import com.android.githubrepositories.domain.ResultWrapper
import com.android.githubrepositories.domain.model.PullRequestModel
import com.android.githubrepositories.domain.model.RepositoriesModel

internal interface GithubRepository {

    suspend fun getRepositories(
        query: String,
        sortMethod: String,
        page: Int
    ): ResultWrapper<RepositoriesModel>

    suspend fun listPullRequests(
        creator: String,
        repository: String
    ): ResultWrapper<List<PullRequestModel>>

}