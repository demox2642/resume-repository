package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.skilbox.shopdb.database.tables.Addresses
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_addresses.*

class AddAddressesFragment : Fragment(R.layout.fragment_add_addresses) {
    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        add_Addresses.setOnClickListener {
            addAddresses(
                city_editText.text.toString(),
                street_editText.text.toString(),
                building_editText.text.toString()
            )
        }
    }

    fun addAddresses(city: String, street: String, building: String) {
        val addresses = listOf(
            Addresses(null, city, street, building)
        )
        viewModel.addAddressesList(addresses)

        findNavController().popBackStack()
    }
}
