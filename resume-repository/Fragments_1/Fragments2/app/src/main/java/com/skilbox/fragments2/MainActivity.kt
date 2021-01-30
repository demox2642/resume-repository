package com.skilbox.fragments2

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), LogOn {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.getBoolean("Login")

        Log.e("MainActivity", "Bundle ${savedInstanceState?.getBoolean("Login")} ")

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.conteiner_intraction, LoginFragment())
            }
        }
    }

    override fun newUserIsSaved(saved: Boolean) {
        if (saved) {

            val savedLog = Bundle()
            savedLog.putBoolean("Login", saved)

            supportFragmentManager.commit {
                replace(R.id.conteiner_intraction, MainFragment(), "MAIN_FRAG")
            }
        }
    }

    override fun onBackPressed() {

        val fragmentMain = supportFragmentManager.findFragmentByTag("MAIN_FRAG")
//        val ifelse = supportFragmentManager.backStackEntryCount
//        val fragmentManagerCount = supportFragmentManager.backStackEntryCount
//        val listFrag = supportFragmentManager.findFragmentByTag("LIST")?.childFragmentManager?.backStackEntryCount
//        val detFrag = supportFragmentManager.findFragmentByTag("DetailFragment")?.childFragmentManager?.backStackEntryCount
//        val mainFrag = supportFragmentManager.findFragmentByTag("MAIN_FRAG")?.childFragmentManager?.backStackEntryCount

       // Log.e("onBackPressed","listFrag = $listFrag || detFrag = $detFrag || mainFrag = $mainFrag|| ifelse = $ifelse || $fragmentMain")
        if (fragmentMain?.childFragmentManager?.backStackEntryCount!! > 0) {

           // Log.e("onBackPressed2","listFrag = $listFrag || detFrag = $detFrag || mainFrag = $mainFrag|| ifelse = $ifelse ||$fragmentMain")
            newUserIsSaved(true)
        } else {
            super.onBackPressed()
        }
    }
}
