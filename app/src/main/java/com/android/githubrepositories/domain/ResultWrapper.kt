package com.android.githubrepositories.domain

internal sealed class ResultWrapper<out T>{
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val message: String? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}
