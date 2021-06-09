package com.skilbox.shopdb

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.AddSupplyAdapter.ProductCountInSupplyAdapter
import com.skilbox.shopdb.database.tables.*
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_supply.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class AddSupply : Fragment(R.layout.fragment_add_supply) {

    private var productForSupplyAdapter: ProductCountInSupplyAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    private var tempNewSuppliersPriceProduct: SuppliersPriceProduct? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getListSuppliesPriceAndProduct()
        initList()
        observViewModelState()

        val productsForSpinner = viewModel.listSuppliesPriceAndProductForFrag.value
        val valueProductsFormSpinner = mutableListOf<String>()
        for (i in productsForSpinner!!.indices) {
            valueProductsFormSpinner.add("продукт:" + productsForSpinner[i].product_name + "  цена:" + productsForSpinner[i].price)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, valueProductsFormSpinner)
        value_product.setAdapter(adapter)
        value_product.setOnItemClickListener { parent, view, position, id ->

            tempNewSuppliersPriceProduct = productsForSpinner[position]
        }

        to_add_product.setOnClickListener {
            if (tempNewSuppliersPriceProduct == null || value_quantity.text!!.isEmpty()) {
                Toast.makeText(requireContext(), "Выберите продукт для доставки и проставьте количество", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addtempProductCountInSupplyLD(

                    ProductCountInSupply(
                        id = tempNewSuppliersPriceProduct!!.id,
                        suppliers_id = tempNewSuppliersPriceProduct!!.suppliers_id,
                        suplilers_name = tempNewSuppliersPriceProduct!!.suplilers_name,
                        product_id = tempNewSuppliersPriceProduct!!.product_id,
                        product_name = tempNewSuppliersPriceProduct!!.product_name,
                        date = tempNewSuppliersPriceProduct!!.date,
                        price = tempNewSuppliersPriceProduct!!.price,
                        product_quantity = value_quantity.text.toString().toInt()
                    )
                )
            }
        }

        add_supply.setOnClickListener {
            val supply = Supply(null, LocalDateTime.now().toInstant(ZoneOffset.UTC), Status.FINISHED.toString())

            val positions = viewModel.tempProductCountInSupply.value
            viewModel.addSupply(supply, positions!!)

            findNavController().popBackStack()
        }
    }

    private fun initList() {
        productForSupplyAdapter = ProductCountInSupplyAdapter()

        with(product_for_supply_recyclerView) {
            adapter = productForSupplyAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.tempProductCountInSupply
            .observe(viewLifecycleOwner) { suppliers -> productForSupplyAdapter?.items = suppliers }
    }
}
