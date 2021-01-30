package com.skilbox.a17_best_practices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val startFragmentWithCarList: StartFragmentWithCarList?
        get() = activity?.let { it as StartFragmentWithCarList }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        linear_layout_manager.setOnClickListener {
            startFragmentWithCarList?.startLLManager()
        }
        grid_layout_manager.setOnClickListener {
            startFragmentWithCarList?.startGLManager()
        }
        staggered_grid_layout_manager.setOnClickListener {
            startFragmentWithCarList?.startAGLManager()
        }
    }
}
