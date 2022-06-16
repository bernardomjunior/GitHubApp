package com.android.githubrepositories.domain.usecase


import com.android.githubrepositories.domain.ResultWrapper
import com.android.githubrepositories.domain.model.RepositoriesModel

internal interface GetKotlinRepositoriesUseCase {

    suspend fun execute(page: Int): ResultWrapper<RepositoriesModel>

}