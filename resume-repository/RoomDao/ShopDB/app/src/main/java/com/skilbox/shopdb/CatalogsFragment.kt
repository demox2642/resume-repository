package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_catalogs.*

class CatalogsFragment : Fragment(R.layout.fragment_catalogs) {

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getProductUnit()

        addresses.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_addressesFragment)
        }

        shops.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_shopsFragment)
        }

        departments.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_departmentsFragment)
        }

        position.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_positionsFragment)
        }

        employers.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_employersFragment)
        }

        products.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_productsFragment)
        }

        suppliers.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_suplesFragment)
        }

        suppliers_price.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_supplyPriceFragment)
        }

        sale_price.setOnClickListener {
            findNavController().navigate(R.id.action_catalogsFragment_to_salePriceFragment)
        }
    }
}
