package com.skilbox.shopdb

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.skilbox.shopdb.database.tables.Products
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_products.*

class AddProductsFragment : Fragment(R.layout.fragment_add_products) {

    val id: Long? = null

    var products_unit_id: Long? = null

    var label: String? = null

    var name: String? = null

    var title: String? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val unitValues = viewModel.productsUnitForFragment.value
        val unitValuesForSpinner = mutableListOf<String>()
        for (i in unitValues!!.indices) {
            unitValuesForSpinner.add(unitValues[i].name)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, unitValuesForSpinner)
        unit_values.setAdapter(adapter)
        unit_values.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                products_unit_id = unitValues[position].id
            }

        add_product_button.setOnClickListener {
            label = value_productLabel.text.toString()
            name = value_productName.text.toString()
            title = value_productTitle.text.toString()

            if (checkBlanc()) {
                val product = Products(null, products_unit_id!!, label!!, name!!, title!!)
                viewModel.addProduct(product)
            } else {
                Toast.makeText(requireContext(), "Не все поля заполнены!!", Toast.LENGTH_LONG)
                    .show()
            }
            findNavController().popBackStack()
        }
    }

    private fun checkBlanc(): Boolean {
        return !(products_unit_id == null || label == null || name == null || title == null)
    }
}
