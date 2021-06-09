package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.addressAdapter.AddressesAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_addresses.*

class AddressesFragment : Fragment(R.layout.fragment_addresses) {

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    private var addressesAdapter: AddressesAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()
        viewModel.getAllAddresses()

        add_addresses_Button.setOnClickListener {
            findNavController().navigate(R.id.action_addressesFragment_to_addAddressesFragment)
        }

        clean_addresses_list.setOnClickListener {
            clianList()
        }
    }

    private fun clianList() {
        viewModel.clianList()
    }

    private fun initList() {
        addressesAdapter = AddressesAdapter { addressesId -> deleteAddress(addressesId) }

        with(addresses_recyclerView) {
            adapter = addressesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteAddress(id: Long) {
        viewModel.deleteAddresses(id)
    }

    private fun observViewModelState() {
        viewModel.addressesLiveDataForFragment
            .observe(viewLifecycleOwner) { addresses -> addressesAdapter?.items = addresses }
    }
}
