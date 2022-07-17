package com.android.githubrepositories.di

import com.android.githubrepositories.BuildConfig.BASE_URL
import com.android.githubrepositories.domain.repository.GithubRepository
import com.android.githubrepositories.domain.usecase.GetKotlinRepositoriesUseCase
import com.android.githubrepositories.domain.usecase.GetKotlinRepositoriesUseCaseImpl
import com.android.githubrepositories.network.GithubApi
import com.android.githubrepositories.network.GithubService
import com.android.githubrepositories.data.GithubRepositoryImpl
import com.android.githubrepositories.domain.usecase.ListRepositoryPullRequestsUseCase
import com.android.githubrepositories.domain.usecase.ListRepositoryPullRequestsUseCaseImpl
import com.android.githubrepositories.ui.pullRequest.PullRequestViewModel
import com.android.githubrepositories.ui.repositoryList.RepositoryListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // networking
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    factory<GithubApi> {
        get<Retrofit>().create(GithubApi::class.java)
    }

    factory {
        GithubService(get(), Dispatchers.IO)
    }

    factory<GithubRepository> {
        GithubRepositoryImpl(get())
    }

    // useCase

    factory<GetKotlinRepositoriesUseCase> {
        GetKotlinRepositoriesUseCaseImpl(get())
    }

    factory<ListRepositoryPullRequestsUseCase>{
        ListRepositoryPullRequestsUseCaseImpl(get())
    }

    // viewModel

    viewModel {
        RepositoryListViewModel(get(), Dispatchers.IO)
    }

    viewModel {
        PullRequestViewModel(get(), Dispatchers.IO)
    }

}