package com.skilbox.activitylifecycle

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(
    val email: String,
    val password: String,
    val egreement: Boolean,
    val message: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readBoolean(),
        parcel.readString().orEmpty()

    )
}
