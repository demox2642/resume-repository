package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.SupplyPriceAdapter.SupplyPriceAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_supply_price.*

class SupplyPriceFragment : Fragment(R.layout.fragment_supply_price) {

    private var supplyPriceAdapter: SupplyPriceAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()
        viewModel.getListSuppliesPriceAndProduct()
        viewModel.getProductList()
        viewModel.geSuppliersList()

        add_supply_price_Button.setOnClickListener {
            findNavController().navigate(R.id.action_supplyPriceFragment_to_addSupplyPriceFragment)
        }

        clean_supply_price_list.setOnClickListener {
            viewModel.clianSuppliers_PriceList()
        }
    }

    private fun initList() {
        supplyPriceAdapter = SupplyPriceAdapter { salePriceID -> deleteSalePrice(salePriceID) }

        with(supply_price_recyclerView) {
            adapter = supplyPriceAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.listSuppliesPriceAndProductForFrag
            .observe(viewLifecycleOwner) { supplyList -> supplyPriceAdapter?.items = supplyList }
    }

    private fun deleteSalePrice(salePrice: Long) {
        viewModel.deleteSuppliers_PriceById(salePrice)
    }
}
