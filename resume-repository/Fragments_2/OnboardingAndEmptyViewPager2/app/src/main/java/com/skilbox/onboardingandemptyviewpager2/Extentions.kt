package com.skilbox.onboardingandemptyviewpager2

import android.os.Bundle

fun <T : Article> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}
