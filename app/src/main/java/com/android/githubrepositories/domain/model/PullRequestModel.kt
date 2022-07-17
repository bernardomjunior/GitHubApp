package com.android.githubrepositories.domain.model

import android.os.Parcelable
import com.android.githubrepositories.network.model.UserResponse
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
internal data class PullRequestModel(
    val user: UserModel,
    val title: String,
    val body: String?,
    val htmlUrl: String,
    val createdAt: Date
): Parcelable