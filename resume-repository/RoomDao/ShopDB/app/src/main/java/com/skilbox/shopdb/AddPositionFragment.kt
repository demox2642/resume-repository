package com.skilbox.shopdb

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_position.*

class AddPositionFragment : Fragment(R.layout.fragment_add_position) {

    private var name: String = ""
    private var departId: Long? = null

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val depart = viewModel.shopsAndDepartForFragment.value

        viewModel.getDepartList()

        add_position.setOnClickListener {

            name = position_name_editText.text.toString()

            if (name.isNotEmpty() && departId != null) {
                viewModel.addPosition(name, departId!!)
                findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Не все данные введены Имя: $name  departId: $departId",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.getItemsForSpinner().observe(
            viewLifecycleOwner,
            { spinnerData ->
                val adapterFatherDept =
                    ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
                spinner_positions.adapter = adapterFatherDept
            }
        )

        spinner_positions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

                    departId = if (position > 0) {
                        depart!![position - 1].departments.id
                    } else {
                        null
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }
}
