package com.skilbox.shopdb

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.ProductForSale.ProductForSaleAdapter
import com.skilbox.shopdb.database.tables.NewSale
import com.skilbox.shopdb.database.tables.Sale
import com.skilbox.shopdb.database.tables.Status
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_sale.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class AddSaleFragment : Fragment(R.layout.fragment_add_sale) {

    private var productForSaleAdapter: ProductForSaleAdapter? = null

    private var tempNewSalePriceProduct: NewSale? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getPreoductForSale()
        initList()
        observViewModelState()

        val productsForSpinner = viewModel.productsForSale.value

        val valueProductsFormSpinner = mutableListOf<String>()
        for (i in productsForSpinner!!.indices) {
            valueProductsFormSpinner.add("продукт:" + productsForSpinner[i].product_name + "  цена:" + productsForSpinner[i].price)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, valueProductsFormSpinner)
        value_product_for_sale.setAdapter(adapter)
        value_product_for_sale.setOnItemClickListener { parent, view, position, id ->

            tempNewSalePriceProduct = productsForSpinner[position]
        }

        to_add_product.setOnClickListener {
            val product_count = value_quantity.text.toString().toInt()
            if (product_count <= tempNewSalePriceProduct!!.count_product) {
                val newSale = NewSale(
                    tempNewSalePriceProduct!!.id!!,
                    tempNewSalePriceProduct!!.product_name, tempNewSalePriceProduct!!.price, product_count, tempNewSalePriceProduct!!.price_id, tempNewSalePriceProduct!!.product_id
                )
                viewModel.addTempProductCountListForSale(newSale)
            } else {
                Toast.makeText(requireContext(), "мы столько продать не сможем!!", Toast.LENGTH_SHORT).show()
            }
        }

        add_sale.setOnClickListener {
            if (viewModel.tempProductCountInSale.value!!.isNotEmpty()) {
                val sale = Sale(null, LocalDateTime.now().toInstant(ZoneOffset.UTC), Status.CREATED.toString())
                viewModel.addSale(sale, viewModel.tempProductCountInSale.value!!)
            } else {
                Toast.makeText(requireContext(), "Добавьте продукты для продажи!!", Toast.LENGTH_SHORT).show()
            }
            findNavController().popBackStack()
        }
    }

    private fun initList() {
        productForSaleAdapter = ProductForSaleAdapter()

        with(product_for_sale_recyclerView) {
            adapter = productForSaleAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.tempProductCountInSale
            .observe(viewLifecycleOwner) { sale -> productForSaleAdapter?.items = sale }
    }
}
