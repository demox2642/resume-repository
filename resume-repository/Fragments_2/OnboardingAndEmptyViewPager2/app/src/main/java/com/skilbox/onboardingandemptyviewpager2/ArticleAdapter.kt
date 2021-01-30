package com.skilbox.onboardingandemptyviewpager2

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.reflect.Array.newInstance

class ArticleAdapter(
    articleScreen: List<ArticleRes>,
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(supportFragmentManager, lifecycle) {

    private var screen: List<ArticleRes> = articleScreen

    override fun getItemCount(): Int {

        return screen.size
    }

    override fun createFragment(position: Int): Fragment {

        Log.e("createFragment", screen.toString())
        val screen: ArticleRes = screen[position]
        return Article.newInstance(
            textRes = screen.textRes,
            drawableRes = screen.imageRes,

        )
    }

    fun updateScreen(articleScreen2: List<ArticleRes>) {
        screen = articleScreen2
        notifyDataSetChanged()
    }

    fun screenShowNow(): List<ArticleRes> {

        Log.e("screenShowNow", screen.toString())
        return screen
    }
}
