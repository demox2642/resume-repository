package com.skilbox.onboardingandemptyviewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.article_fragment.*
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.presentation_fragment.*

class Article : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        articleImegeview.setImageResource(requireArguments().getInt("drawable"))
        articleTextView.text = getText(requireArguments().getInt("text"))

        showMeEvent.setOnClickListener {
            activity?.tabLayout2?.getTabAt((0 until activity?.tabLayout2?.tabCount!!).random())?.orCreateBadge?.apply {
                number += 3
                badgeGravity = BadgeDrawable.TOP_END
            }
        }
    }

    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_DRAWABLE = "drawable"

        fun newInstance(
            @StringRes textRes: Int,
            @DrawableRes drawableRes: Int,

        ): Article {
            return Article().withArguments {

                putInt(KEY_TEXT, textRes)
                putInt(KEY_DRAWABLE, drawableRes)
            }
        }
    }
}
