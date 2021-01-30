package com.skilbox.onboardingandemptyviewpager2.Onboarding

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingFragmentAdapter(
    onboardingScreens: List<OnboardingScreen>,
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(supportFragmentManager, lifecycle) {

    private val onboardingScreens = onboardingScreens

    override fun getItemCount(): Int {
        return onboardingScreens.size
    }

    override fun createFragment(position: Int): Fragment = OnboardingPresentation().apply {
        arguments = bundleOf(
            "position" to position,
            "size" to itemCount,
            "bgColorRes" to onboardingScreens[position].bgColorRes,
            "drawableRes" to onboardingScreens[position].drawableRes,
            "textRes" to onboardingScreens[position].textRes,
            "textColorRes" to onboardingScreens[position].textColorRes

        )
    }
}
