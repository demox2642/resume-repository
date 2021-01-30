package com.skilbox.onboardingandemptyviewpager2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArticleRes(
    @StringRes val textRes: Int,
    @DrawableRes val imageRes: Int,
    val tagRes: List<ArticleTag>,
    val articleName: String
)
