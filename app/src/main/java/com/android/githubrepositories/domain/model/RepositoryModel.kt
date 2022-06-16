package com.android.githubrepositories.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class RepositoryModel(
    val name: String,
    val description: String,
    val starCount: Int,
    val forksCount: Int,
    val owner: UserModel
) : Parcelable
