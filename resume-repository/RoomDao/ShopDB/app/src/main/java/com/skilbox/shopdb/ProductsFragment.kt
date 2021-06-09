package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.productsAdapter.ProductsAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private var productsAdapter: ProductsAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()
        viewModel.getProductList()
        viewModel.insertProducts_Unit()

        add_product_Button.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_addProductsFragment)
        }

        clean_product_list.setOnClickListener {
            viewModel.cleanProductList()
        }
    }

    private fun initList() {
        productsAdapter = ProductsAdapter { productID -> deleteProduct(productID) }

        with(product_recyclerView) {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.productsListForFragments
            .observe(viewLifecycleOwner) { product -> productsAdapter?.items = product }
    }

    private fun deleteProduct(productID: Long) {
        viewModel.deleteProduct(productID)
    }
}
