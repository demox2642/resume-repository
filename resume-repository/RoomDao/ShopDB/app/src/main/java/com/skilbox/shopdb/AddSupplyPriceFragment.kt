package com.skilbox.shopdb

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.skilbox.shopdb.database.tables.Supplies_Price
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_sale_price.value_product
import kotlinx.android.synthetic.main.fragment_add_supply_price.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class AddSupplyPriceFragment : Fragment(R.layout.fragment_add_supply_price) {

    var suppliers_id: Long? = null

    var product_id: Long? = null

    var date: Instant? = null

    var price: BigDecimal? = null

    private val outputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val calendarBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date ")

        val picker: MaterialDatePicker<*> = calendarBuilder.build()

        value_supply_price_date.setOnClickListener {
            picker.show(childFragmentManager, picker.toString())
        }

        picker.addOnPositiveButtonClickListener {

            date = outputDateFormat.parse(outputDateFormat.format(it)).toInstant()
            value_supply_price_date.setText(outputDateFormat.format(it))
        }

        val supplyList = viewModel.suppliersListForFragment.value
        val supplyValuesForSpinner = mutableListOf<String>()
        for (i in supplyList!!.indices) {
            supplyValuesForSpinner.add(supplyList[i].suppliers.name)
        }
        val supplyAdapter = ArrayAdapter(requireContext(), R.layout.list_item, supplyValuesForSpinner)
        value_supply.setAdapter(supplyAdapter)
        value_supply.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                suppliers_id = supplyList[position].suppliers.id
            }

        val productsValues = viewModel.productsListForFragments.value
        val productsValuesForSpinner = mutableListOf<String>()
        for (i in productsValues!!.indices) {
            productsValuesForSpinner.add(productsValues[i].products.name)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, productsValuesForSpinner)
        value_product.setAdapter(adapter)
        value_product.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                product_id = productsValues[position].products.id
            }

        add_supply_price.setOnClickListener {
            price = value_supply_price.text.toString().toBigDecimal()

            if (checkBlanc()) {
                val supply_price = Supplies_Price(null, suppliers_id!!, product_id!!, date!!, price!!)
                viewModel.addSuppliersPrice(supply_price)
            } else {
                Toast.makeText(requireContext(), "Не все поля заполнены!!", Toast.LENGTH_LONG)
                    .show()
            }
            findNavController().popBackStack()
        }
    }

    private fun checkBlanc(): Boolean {
        return !(suppliers_id == null||price == null || date == null || product_id == null)
    }
}
