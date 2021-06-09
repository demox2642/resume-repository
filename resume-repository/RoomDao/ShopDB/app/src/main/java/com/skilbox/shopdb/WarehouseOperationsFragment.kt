package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_warehouse_operations.*

class WarehouseOperationsFragment : Fragment(R.layout.fragment_warehouse_operations) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sale.setOnClickListener {
            findNavController().navigate(R.id.action_warehouseOperationsFragment_to_sale)
        }

        supply.setOnClickListener {
            findNavController().navigate(R.id.action_warehouseOperationsFragment_to_suppliy)
        }

        leftovers.setOnClickListener {
            findNavController().navigate(R.id.action_warehouseOperationsFragment_to_leftoversFragment)
        }
    }
}
