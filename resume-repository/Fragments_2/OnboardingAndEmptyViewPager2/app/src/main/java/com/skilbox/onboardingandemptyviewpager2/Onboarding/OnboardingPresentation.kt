package com.skilbox.onboardingandemptyviewpager2.Onboarding

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.skilbox.onboardingandemptyviewpager2.R
import kotlinx.android.synthetic.main.fragment_onboarding.*
import kotlinx.android.synthetic.main.fragment_onboarding.view.*
import kotlinx.android.synthetic.main.presentation_fragment.*
import kotlinx.android.synthetic.main.presentation_fragment.view.*

@Suppress("DEPRECATION")
class OnboardingPresentation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.presentation_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            val position = it.getInt("position")
            val size = it.getInt("size") - 1

            presentationTextView.text = getText(it.getInt("textRes"))
            presentationTextView.setTextColor(resources.getColor(it.getInt("textColorRes")))
            presentationLinerLayout.setBackgroundResource(it.getInt("bgColorRes"))
            presentationImageView.setImageResource(it.getInt("drawableRes"))

            if (position == size) {
                exitButton.text = "Выход"
            }

            exitButton.setOnClickListener {
                Log.e("NextButton", "Next is click")
                if (position == size) {
                    findNavController().navigate(R.id.action_onboardingFragment_to_articleFragment)
                } else {
                    // val carClass: Array<String> = arrayOf("Класс A", "Класс B", "Класс C", "Класс D", "Класс E", "Класс J")
                    AlertDialog.Builder(it.context)
                        .setTitle(" Пропустить презентацию?:")
                        .setPositiveButton("Ok") {
                            _, _ ->
                            findNavController().navigate(R.id.action_onboardingFragment_to_articleFragment)
                        }
                        .setNegativeButton("Отмена") {
                            dialog, _ ->
                            dialog.cancel()
                        }

                        .show()
                }
            }
        }

        // val position = getInt("position")
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}
