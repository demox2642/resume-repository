package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.suppliersAdapter.SuppliersAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_suples.*

class SuppliersFragment : Fragment(R.layout.fragment_suples) {

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    private var suppliersAdapter: SuppliersAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()
        viewModel.getAllAddresses()
        viewModel.geSuppliersList()

        add_suppliers_Button.setOnClickListener {
            findNavController().navigate(R.id.action_suplesFragment_to_addSuplesFragment)
        }

        clean_suppliers_list.setOnClickListener {
            viewModel.cleanSuppliesList()
        }
    }

    private fun initList() {
        suppliersAdapter = SuppliersAdapter { suppliersId -> deleteSuppliers(suppliersId) }

        with(suppliers_recyclerView) {
            adapter = suppliersAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteSuppliers(suppliersId: Long) {
        viewModel.deleteSuppliersDyId(suppliersId)
    }

    private fun observViewModelState() {
        viewModel.suppliersListForFragment
            .observe(viewLifecycleOwner) { suppliers -> suppliersAdapter?.items = suppliers }
    }
}
