package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.departmentsAdapter.DepartmentsAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_departments.*

class DepartmentsFragment : Fragment(R.layout.fragment_departments) {

    private var departAdapter: DepartmentsAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        observViewModelState()
        viewModel.getDepartList()
        viewModel.getShopsList()

        viewModel.getShopsAndDeparts()

        add_depart_Button.setOnClickListener {
            findNavController().navigate(R.id.action_departmentsFragment_to_addDepartmentsFragment)
        }
    }

    private fun initList() {
        departAdapter = DepartmentsAdapter { deptsId -> deleteDepartment(deptsId) }

        with(depart_recyclerView) {
            adapter = departAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteDepartment(id: Long) {
        viewModel.deleteDepartmentsById(id)
    }

    private fun observViewModelState() {
        viewModel.deparrtLiveDataForFragment
            .observe(viewLifecycleOwner) { deptments -> departAdapter?.items = deptments }
    }
}
