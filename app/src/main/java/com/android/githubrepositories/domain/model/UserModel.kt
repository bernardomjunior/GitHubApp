package com.android.githubrepositories.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class UserModel(
    val avatar: String,
    val nickName: String
) : Parcelable
