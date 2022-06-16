package com.android.githubrepositories.network.exception

internal class ConnectionError : Exception()
internal class RequestError(reason: String?) : Exception(reason)

