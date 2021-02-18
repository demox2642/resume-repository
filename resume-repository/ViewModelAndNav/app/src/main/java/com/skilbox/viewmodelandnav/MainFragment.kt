package com.skilbox.viewmodelandnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        linear_layout_manager.setOnClickListener {
            view?.findNavController()?.navigate(R.id.mainFragment_to_lLMFragment)
        }
        grid_layout_manager.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainFragment_to_GLM)
        }
        staggered_grid_layout_manager.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainFragment_to_SGLM)
        }
    }
}
