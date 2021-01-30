package com.skilbox.fragments2

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainFragment : Fragment(R.layout.main_fragment), ItemSelectList {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation: Boolean = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        val test = savedInstanceState?.toString() // getIntegerArrayList("key_text") ==null


        val fragmentMain = childFragmentManager.findFragmentById(R.id.container_for_child_fragment)
        val tests = childFragmentManager?.backStackEntryCount

        Log.e("TestOrient", "Fragment = $tests|| orientation = $orientation|| saveInstans = $test")

        if (orientation) {
            Log.e("TestOrient1", "Fragment = $tests|| orientation = $orientation|| saveInstans = $test ||   FragName = $fragmentMain")
            if (childFragmentManager?.backStackEntryCount> 0 && savedInstanceState?.getIntegerArrayList("key_text") == null) {
                Log.e("TestOrient2", "Fragment = $tests|| orientation = $orientation|| saveInstans = $test")

                onItemSelect("")
            } else {

                val fragment: Fragment? = childFragmentManager?.findFragmentByTag("LIST")
                if (fragment != null) childFragmentManager.beginTransaction().remove(fragment).commit()
                Log.e("TestOrient3", "Fragment = $tests|| orientation = $orientation|| saveInstans = $test ||   FragName = $fragmentMain || fragment = $fragment")
                startList()


            }
        } else {
            Log.e("TestOrient4", "Fragment = $tests|| orientation = $orientation|| saveInstans = $test")
            if (childFragmentManager?.backStackEntryCount != 0) {
                val fragment: Fragment? = childFragmentManager?.findFragmentByTag("LIST")
                Log.e("TestOrient5", "Fragment = $tests|| orientation = $orientation|| saveInstans = $test ||   FragName = $fragmentMain")
                if (fragment != null) childFragmentManager.popBackStack("DetailFragment",0)
            } else {
                Log.e("TestOrient6", "Fragment = $tests|| orientation = $orientation|| saveInstans = $test  ||   FragName = $fragmentMain")
                val fragment: Fragment? = childFragmentManager?.findFragmentByTag("LIST")

                if (fragment != null) childFragmentManager.beginTransaction().remove(fragment).commit()

                onItemSelect("")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, null)
        Log.e("TestOrient10", "savedInstanceState = $savedInstanceState")
    }

    override fun onItemSelect(text: String) {

        Log.e("MainFragment", "I'm in fun IntractionActivity / onItemSelect  ")
        if (text.isNotEmpty()) {
            childFragmentManager.beginTransaction()
                .replace(R.id.container_for_child_fragment, DetailFragment.newInstance(text), "DetailFragment")
                .addToBackStack("DetailFragment")
                .commit()
        }
    }

    private fun startList() {
        Log.e("MainFragment_startList", "I'm in fun onActivityCreated ")

        val orientation: Boolean = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        childFragmentManager.commit {
            replace(R.id.container_for_child_fragment, ListFragment(), "LIST")
            // setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            if (!orientation) addToBackStack("LIST")
        }
        //childFragmentManager.beginTransaction().remove(ListFragment()).commit()
    }

//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        Log.e("TestOrient10", "savedInstanceState = $savedInstanceState")
//        Bundle().clear()
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        Log.e("TestOrient10", "savedInstanceState = $savedInstanceState")
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        Log.e("TestOrient10", "savedInstanceState = $savedInstanceState")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.e("TestOrientonStart", "savedInstanceState = null")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.e("TestOrientonResume", "savedInstanceState = null")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.e("TestOrientonPause", "savedInstanceState = null")
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.e("TestOrienInstanceState", "savedInstanceState = null")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.e("TestOrientonStop", "savedInstanceState = null")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.e("TestOrientonDestroy", "savedInstanceState = null")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        Log.e("TestOrientonDestroyView", "savedInstanceState = null")
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        Log.e("TestOrientonDetach", "savedInstanceState = null")
//    }
}
