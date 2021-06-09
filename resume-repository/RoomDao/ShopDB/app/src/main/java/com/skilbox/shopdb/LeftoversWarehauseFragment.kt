package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.LeftoversFragment.LeftoversFragmentAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_leftovers.*

class LeftoversWarehauseFragment : Fragment(R.layout.fragment_leftovers) {

    private var leftoversAdapter: LeftoversFragmentAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observViewModelState()
        initList()
        viewModel.getAllProductsInWarehouse()
    }

    private fun initList() {
        leftoversAdapter = LeftoversFragmentAdapter()

        with(warehouse_recyclerView) {
            adapter = leftoversAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.productsInWarehouseForFragment
            .observe(viewLifecycleOwner) { products -> leftoversAdapter?.items = products }
    }
}
