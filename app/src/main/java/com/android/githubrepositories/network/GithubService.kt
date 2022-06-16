package com.android.githubrepositories.network

import com.android.githubrepositories.network.exception.ConnectionError
import com.android.githubrepositories.network.exception.RequestError
import com.android.githubrepositories.network.model.ErrorResponse
import com.android.githubrepositories.network.model.RepositoriesResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

internal class GithubService(
    private val api: GithubApi,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getRepositories(
        query: String,
        sortMethod: String,
        pageNum: Int
    ): RepositoriesResponse {
        return withContext(dispatcher){
            try {
                 api.getRepositories(
                    query = query,
                    sortMethod = sortMethod,
                    pageNumber = pageNum
                )
            } catch (err: Exception) {
                throw when (err) {
                    is IOException -> {
                        ConnectionError()
                    }
                    is HttpException -> {
                        RequestError(convertErrorBody(err).message)
                    }
                    else -> {
                        RequestError(null)
                    }
                }
            }
        }

    }

    private fun convertErrorBody(error: HttpException): ErrorResponse {
        return try {
            val text = error.response()?.errorBody()?.string()
            Gson().fromJson(text, ErrorResponse::class.java)
        } catch (error: IOException) {
            ErrorResponse(null)
        }
    }

}