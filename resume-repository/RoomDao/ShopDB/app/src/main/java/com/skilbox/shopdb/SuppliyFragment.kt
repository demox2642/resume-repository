package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.SuppliyFragmentAdapter.SuppliyFragmentAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_suppliy.*

class SuppliyFragment : Fragment(R.layout.fragment_suppliy) {
    private var supplyAdapter: SuppliyFragmentAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        observViewModelState()
        viewModel.getTotalSupply()
        viewModel.getListSuppliesPriceAndProduct()
        viewModel.geSuppliersList()
        viewModel.getSupplyDetailAndPrice()

        add_supply_Button.setOnClickListener {
            findNavController().navigate(R.id.action_suppliy_to_addSupply)
        }

        clean_supply_list.setOnClickListener {
            viewModel.clianSupplyDetail()
            viewModel.clianSupplyList()
        }
    }

    private fun initList() {
        supplyAdapter = SuppliyFragmentAdapter { suppliersId -> buttonClick(suppliersId) }

        with(supply_recyclerView) {
            adapter = supplyAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun buttonClick(suppliersId: Long) {
        viewModel.deleteSuppliersDyId(suppliersId)
    }

    private fun observViewModelState() {
        viewModel.totalSupplyForFragment
            .observe(viewLifecycleOwner) { suppliers -> supplyAdapter?.items = suppliers }
    }
}
