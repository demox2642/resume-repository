package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.SalePriceAdapter.SalePriceAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_sale_price.*

class SalePriceFragment : Fragment(R.layout.fragment_sale_price) {

    private var salePricetAdapter: SalePriceAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()
        viewModel.getAllSale_Price()
        viewModel.getProductList()

        add_sale_price_Button.setOnClickListener {
            findNavController().navigate(R.id.action_salePriceFragment_to_addSalePriceFragment)
        }

        clean_sale_price_list.setOnClickListener {
            viewModel.clianSale_PriceList()
        }
    }

    private fun initList() {
        salePricetAdapter = SalePriceAdapter { salePriceID -> deleteSalePrice(salePriceID) }

        with(sale_price_recyclerView) {
            adapter = salePricetAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.salePriceForFragment
            .observe(viewLifecycleOwner) { product -> salePricetAdapter?.items = product }
    }

    private fun deleteSalePrice(salePrice: Long) {
        viewModel.deleteSale_PriceById(salePrice)
    }
}
