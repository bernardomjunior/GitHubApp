package com.android.githubrepositories.ui

import androidx.annotation.StringRes

internal sealed class ViewState<out R>{
    data class Success<out T>(val data: T): ViewState<T>()
    data class Failure(val message: String? = null, @StringRes val resId: Int? = null): ViewState<Nothing>()
    object Loading: ViewState<Nothing>()
}
