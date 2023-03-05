package com.android.githubrepositories.network

import com.android.githubrepositories.network.model.PullRequestResponse
import com.android.githubrepositories.network.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GithubApi {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sortMethod: String,
        @Query("page") pageNumber: Int
    ): RepositoriesResponse

    @GET("repos/{creator}/{repository}/pulls")
    suspend fun listPullRequests(
        @Path("creator") creator: String,
        @Path("repository") repository: String
    ): List<PullRequestResponse>

}