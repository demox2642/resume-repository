package com.skilbox.onboardingandemptyviewpager2.Onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skilbox.onboardingandemptyviewpager2.R
import kotlinx.android.synthetic.main.fragment_onboarding.*
import kotlinx.android.synthetic.main.fragment_onboarding.view.*
import kotlinx.android.synthetic.main.presentation_fragment.*
import kotlinx.android.synthetic.main.presentation_fragment.view.*

class OnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        retainInstance = true

        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        val onboardingScreens: List<OnboardingScreen> = listOf(
            OnboardingScreen(
                textRes = R.string.presentation_text_1,
                bgColorRes = R.color.onboarding_color_1,
                drawableRes = R.drawable.onboarding_1,
                textColorRes = R.color.onboarding_text_color_1
            ),
            OnboardingScreen(
                textRes = R.string.presentation_text_2,
                bgColorRes = R.color.onboarding_color_2,
                drawableRes = R.drawable.onboarding_2,
                textColorRes = R.color.onboarding_text_color_2
            ),
            OnboardingScreen(
                textRes = R.string.presentation_text_3,
                bgColorRes = R.color.onboarding_color_3,
                drawableRes = R.drawable.onboarding_3,
                textColorRes = R.color.onboarding_text_color_3
            )
        )

        val adapter = OnboardingFragmentAdapter(
            onboardingScreens,
            childFragmentManager,
            lifecycle
        )

        view.viewPagerOnOnboarding.adapter = adapter

        return view
    }
}
