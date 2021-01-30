package com.skilbox.fragments2

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment

class   ListFragment : Fragment(R.layout.fragment_list) {

    private val parent: ItemSelectList
        get() = (parentFragment as ItemSelectList)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Log.e("ListFragment", "I'm in fun onAttach activity= $activity")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Log.e("ListFragment", "I'm in fun onActivityCreated activity= $activity")

        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach {
                button ->
                button.setOnClickListener {
                    onButtonClick(button.text.toString())
                }
            }
    }

    private fun onButtonClick(buttonText: String) {

//        Log.e(
//            "ListFragment",
//            "I'm in fun onButtonClick activity= $activity" +
//                "buttonText = $buttonText"
//        )

        parent?.onItemSelect(buttonText)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Log.e("ListFragment", "By onDestroyView ")
    }

    override fun onDetach() {
        super.onDetach()
        //  Log.e("ListFragment", "By onDetach ")
    }

    override fun onDestroy() {
        super.onDestroy()
        // Log.e("ListFragment", "By onDestroy ")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Log.e("onConfigurationChanged", "I'm in ListFragment")
    }
}
