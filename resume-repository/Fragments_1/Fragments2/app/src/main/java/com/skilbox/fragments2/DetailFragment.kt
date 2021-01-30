package com.skilbox.fragments2

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment() : Fragment(R.layout.detail_fragment) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("MyApp", "onActivityCreated DetailFragment")
        fragmentText.text = requireArguments().getString(KEY_TEXT)
    }

    companion object {
        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): Fragment {

            Log.e("MyApp", "newInstance  text= $text   DetailFragment")

            return DetailFragment().withArguments {
                putString(KEY_TEXT, text)
            }

            // Log.e("MyApp", "newInstance  text= ${DetailFragment().withArguments { getString(KEY_TEXT) }} ")
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e("onConfigurationChanged", "I'm in DetailFragment")
    }
}
