package com.skilbox.shopdb

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.skilbox.shopdb.database.tables.Addresses
import com.skilbox.shopdb.database.tables.Suppliers
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_suples.*

class AddSuplesFragment : Fragment(R.layout.fragment_add_suples) {

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    private var addressesFromDB = mutableListOf<Addresses>()
    private var name = ""
    private var addressesId: Long? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val addresses = mutableListOf<String>()
        addressesFromDB =
            viewModel.addressesLiveDataForFragment.value?.toMutableList() ?: mutableListOf()
        for (element in addressesFromDB) {
            addresses.add("город: ${element.city} улица: ${element.street} дом: ${element.building}")
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, addresses)
        addresses_values.setAdapter(adapter)
        addresses_values.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                addressesId = addressesFromDB[position].id
            }

        add_suppliers_button.setOnClickListener {
            name = value_suppliersName.text.toString()
            if (checkBlanc()) {
                val supplies = Suppliers(null, addressesId!!, name)
                viewModel.addSupplies(supplies)
            } else {
                Toast.makeText(requireContext(), "Не все поля заполнены!!", Toast.LENGTH_LONG)
                    .show()
            }
            findNavController().popBackStack()
        }
    }

    private fun checkBlanc(): Boolean {
        return !(name.isEmpty() || addressesId == null)
    }
}
