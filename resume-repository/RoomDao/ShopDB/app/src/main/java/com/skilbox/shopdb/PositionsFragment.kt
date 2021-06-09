package com.skilbox.shopdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.shopdb.positionAdapter.PositionAdapter
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_positions.*

class PositionsFragment : Fragment(R.layout.fragment_positions) {

    private var positionAdapter: PositionAdapter? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observViewModelState()
        initList()
        viewModel.getPositionWhithPlace()
        viewModel.getShopsAndDeparts()

        add_position_Button.setOnClickListener {
            findNavController().navigate(R.id.action_positionsFragment_to_addPositionFragment)
        }

        clean_position_list.setOnClickListener {
            viewModel.clianPositionList()
        }
    }

    private fun initList() {
        positionAdapter = PositionAdapter { positionId -> deletePosition(positionId) }

        with(position_recyclerView) {
            adapter = positionAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        viewModel.positionsForFragment
            .observe(viewLifecycleOwner) { positions -> positionAdapter?.items = positions }
    }

    private fun deletePosition(positionId: Long) {
        viewModel.deletePosition(positionId = positionId)
    }
}
