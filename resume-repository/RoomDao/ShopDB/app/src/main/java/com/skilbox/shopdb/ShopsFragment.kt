package com.skilbox.shopdb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.shopsAdapter.ShopsAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_shops.*

class ShopsFragment : Fragment(R.layout.fragment_shops) {

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    private var shopdAdapter: ShopsAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()
        viewModel.getAllAddresses()
        viewModel.getShopsList()

        add_shops_Button.setOnClickListener {
            findNavController().navigate(R.id.action_shopsFragment_to_addShopFragment)
        }

        clean_shops_list.setOnClickListener {
            viewModel.clianShopList()
        }
    }

    private fun initList() {
        shopdAdapter = ShopsAdapter { shopId -> deleteShop(shopId) }

        with(shops_recyclerView) {
            adapter = shopdAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteShop(id: Long) {
        Log.e("deleteShop", "$id")
        viewModel.deleteShop(id)
    }

    private fun observViewModelState() {
        viewModel.shopsLiveDataForFragment
            .observe(viewLifecycleOwner) { addresses -> shopdAdapter?.items = addresses }
    }
}
