package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.employerAdapter.EmployerAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_employers.*

class EmployersFragment : Fragment(R.layout.fragment_employers) {

    private var employerAdapter: EmployerAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()

        viewModel.getPositionWhithPlace()

        viewModel.getEmploersPositionPlace()

        add_employrs_Button.setOnClickListener {
            findNavController().navigate(R.id.action_employersFragment_to_addEmployerFragment)
        }

        clean_employrs_list.setOnClickListener {
            viewModel.cleanEmployerList()
        }
    }

    private fun initList() {
        employerAdapter = EmployerAdapter { empId -> deleteEmployer(empId) }

        with(employrs_recyclerView) {
            adapter = employerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.emploersPositionPlaceForFragment
            .observe(viewLifecycleOwner) { employers -> employerAdapter?.items = employers }
    }

    private fun deleteEmployer(empId: Long) {
        viewModel.deleteEmployerById(empId)
    }
}
