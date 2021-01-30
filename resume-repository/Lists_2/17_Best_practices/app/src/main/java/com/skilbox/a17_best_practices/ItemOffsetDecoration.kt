package com.skilbox.a17_best_practices

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(
    private val context: Context
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = 10.fromDpToPixcel(context)
        with(outRect) {
            left = offset
            right = offset
            bottom = offset
            top = offset
        }
    }

    private fun Int.fromDpToPixcel(context: Context): Int {
        val density = context.resources.displayMetrics.densityDpi
        val pixcelsInDp = density / DisplayMetrics.DENSITY_DEFAULT
        return this * pixcelsInDp
    }
}
