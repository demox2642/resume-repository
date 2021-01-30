package com.skilbox.fragments2

import android.os.Bundle

fun <T : DetailFragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}
