package com.skilbox.shopdb

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.skilbox.shopdb.database.tables.Sale_Price
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_sale_price.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class AddSalePriceFragment : Fragment(R.layout.fragment_add_sale_price) {

    var product_id: Long? = null
    var date: Instant? = null
    var price: String? = null

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

        value_sale_price_date.setOnClickListener {
            picker.show(childFragmentManager, picker.toString())
        }

        picker.addOnPositiveButtonClickListener {

            date = outputDateFormat.parse(outputDateFormat.format(it)).toInstant()
            value_sale_price_date.setText(outputDateFormat.format(it))
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

        add_sale_price.setOnClickListener {
            price = value_sale_price.text.toString()

            if (checkBlanc()) {
                val sale_price = Sale_Price(null, product_id!!, date!!, price!!.toBigDecimal())
                viewModel.addSalePrice(sale_price)
            } else {
                Toast.makeText(requireContext(), "Не все поля заполнены!!", Toast.LENGTH_LONG)
                    .show()
            }
            findNavController().popBackStack()
        }
    }

    private fun checkBlanc(): Boolean {
        return !(price == null|| price!!.isEmpty() || date == null || product_id == null)
    }
}
