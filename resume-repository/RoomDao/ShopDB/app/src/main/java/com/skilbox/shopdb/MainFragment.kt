package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Timber.e("MainFrag")

        catalogs.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_catalogsFragment)
        }

        warehouse_operations.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_warehouseOperationsFragment)
        }
    }
}
