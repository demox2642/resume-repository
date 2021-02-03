package com.skilbox.a18_data_time

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {

    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
