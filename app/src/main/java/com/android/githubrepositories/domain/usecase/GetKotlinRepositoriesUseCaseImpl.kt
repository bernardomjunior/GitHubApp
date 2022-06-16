package com.android.githubrepositories.domain.usecase


import com.android.githubrepositories.domain.ResultWrapper
import com.android.githubrepositories.domain.model.RepositoriesModel
import com.android.githubrepositories.domain.repository.GithubRepository

internal class GetKotlinRepositoriesUseCaseImpl(
    private val repository: GithubRepository
): GetKotlinRepositoriesUseCase {

    companion object {
        private const val KOTLIN_LANGUAGE = "language:kotlin"
        private const val DEFAULT_SORTING = "stars"
    }

    override suspend fun execute(page: Int): ResultWrapper<RepositoriesModel> {
        return repository.getRepositories(
            query = KOTLIN_LANGUAGE,
            sortMethod = DEFAULT_SORTING,
            page = page
        )
    }
}