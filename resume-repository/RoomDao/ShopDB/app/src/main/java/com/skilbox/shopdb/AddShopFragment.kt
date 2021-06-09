package com.skilbox.shopdb

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.skilbox.shopdb.database.tables.Addresses
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_shop.*

class AddShopFragment : Fragment(R.layout.fragment_add_shop) {

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    private var addressesFromDB = mutableListOf<Addresses>()
    private var name = ""
    private var addressesId: Long? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadAddressesList()

        add_shop.setOnClickListener {
            addShopInDB()
        }

        val addresses = mutableListOf<String>()
        for (element in addressesFromDB) {
            addresses.add("город: ${element.city} улица: ${element.street} дом: ${element.building}")
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            addresses
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner_addresses.adapter = adapter

        spinner_addresses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                addressesId = addressesFromDB[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun addShopInDB() {
        name = shop_name_editText.text.toString()
        if (name.isEmpty() || addressesId == null) {
            Toast.makeText(
                requireContext(),
                "Не все данные введены name=$name addres=$addressesId",
                Toast.LENGTH_LONG
            ).show()
        } else {
            viewModel.addShop(name = name, addressesId = addressesId!!)
            findNavController().popBackStack()
        }
    }

    private fun loadAddressesList() {

        addressesFromDB =
            viewModel.addressesLiveDataForFragment.value?.toMutableList() ?: mutableListOf()
    }
}
