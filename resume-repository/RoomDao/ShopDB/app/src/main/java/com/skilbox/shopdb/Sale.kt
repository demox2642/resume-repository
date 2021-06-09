package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.totalSaleAdapter.TotalSaleAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_sale.*

class Sale : Fragment(R.layout.fragment_sale) {

    private var salelyAdapter: TotalSaleAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTotalSale()
        initList()
        observViewModelState()
        viewModel.getPreoductForSale()

        add_sale_Button.setOnClickListener {
            findNavController().navigate(R.id.action_sale_to_addSaleFragment)
        }

        clean_sale_list.setOnClickListener {
            viewModel.clianSaleList()
        }
    }

    private fun initList() {
        salelyAdapter = TotalSaleAdapter { suppliersId -> buttonClick(suppliersId) }

        with(sale_recyclerView) {
            adapter = salelyAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun buttonClick(suppliersId: Long) {
        viewModel.deleteSaile(suppliersId)
    }

    private fun observViewModelState() {
        viewModel.totalSaleForFragment
            .observe(viewLifecycleOwner) { suppliers -> salelyAdapter?.items = suppliers }
    }
}
