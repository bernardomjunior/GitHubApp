package com.android.githubrepositories.network

import com.android.githubrepositories.network.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface GithubApi {

    @GET("search/repositories?q=language:kotlin&sort=stars&page=1")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sortMethod: String,
        @Query("page") pageNumber: Int
    ): RepositoriesResponse

}