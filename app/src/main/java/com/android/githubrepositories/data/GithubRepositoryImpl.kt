package com.android.githubrepositories.data

import com.android.githubrepositories.domain.ResultWrapper
import com.android.githubrepositories.domain.model.PullRequestModel
import com.android.githubrepositories.domain.model.RepositoriesModel
import com.android.githubrepositories.domain.repository.GithubRepository
import com.android.githubrepositories.network.GithubService
import com.android.githubrepositories.network.exception.ConnectionError
import com.android.githubrepositories.network.exception.RequestError

internal class GithubRepositoryImpl(
    private val service: GithubService
) : GithubRepository {

    override suspend fun getRepositories(
        query: String,
        sortMethod: String,
        page: Int
    ): ResultWrapper<RepositoriesModel> =
        try {
            ResultWrapper.Success(
                service.getRepositories(query, sortMethod, page).toModel()
            )
        } catch (err: Exception) {
            when (err) {
                is ConnectionError -> {
                    ResultWrapper.NetworkError
                }
                is RequestError -> {
                    ResultWrapper.GenericError(err.message)
                }
                else -> {
                    ResultWrapper.GenericError()
                }
            }
        }

    override suspend fun listPullRequests(
        creator: String,
        repository: String
    ): ResultWrapper<List<PullRequestModel>> {
        return try {
            ResultWrapper.Success(
                service.listPullRequests(creator, repository).toModel()
            )
        } catch (err: Exception) {
            when (err) {
                is ConnectionError -> {
                    ResultWrapper.NetworkError
                }
                is RequestError -> {
                    ResultWrapper.GenericError(err.message)
                }
                else -> {
                    ResultWrapper.GenericError()
                }
            }
        }
    }
}