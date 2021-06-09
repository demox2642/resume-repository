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
import kotlinx.android.synthetic.main.fragment_add_departments.*

class AddDepartmentsFragment : Fragment(R.layout.fragment_add_departments) {

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    private var departName = ""
    private var departShortName = ""
    private var parentDepart: Long? = null
    private var shopId: Long? = null

    var adapterFatherDept: ArrayAdapter<String>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        add_depart.setOnClickListener {
            addDepartInDB()
        }

        loadParentList()

        val shopsFromDB =
            viewModel.shopsLiveDataForFragment.value?.toMutableList() ?: mutableListOf()
        val shopsForSpiner = mutableListOf<String>("")
        for (element in shopsFromDB) {
            shopsForSpiner.add("магазин: ${element.shops.name} ")
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            shopsForSpiner
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner_shops.adapter = adapter

        spinner_shops.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position > 0) {

                    shopId = shopsFromDB[position - 1].shops.id
                    if (shopsFromDB.isNotEmpty()) {
                        viewModel.getShopsAndDepartsFilter(shopsFromDB[position - 1].shops.id!!)
                    }
                    loadParentList()
                } else {
                    null
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                shopId = null
            }
        }
    }

    private fun addDepartInDB() {
        departName = depart_name_editText.text.toString()
        departShortName = depart_nameShort_editText.text.toString()

        if (departName.isEmpty() || departShortName.isEmpty() || shopId == null) {
            Toast.makeText(
                requireContext(),
                "Не все данные введены departName=$departName departShortName=$departShortName shopId=$shopId",
                Toast.LENGTH_LONG
            ).show()
        } else {
            viewModel.addDepart(parentDepart, shopId!!, departName, departShortName)
            findNavController().popBackStack()
        }
    }

    private fun loadParentList() {

        val fatherdepart = viewModel.shopsAndDepartForFragment.value!!.toMutableList()

        if (fatherdepart.isEmpty()) {
            spinner_parentDeparts.visibility = View.GONE
        } else {
            spinner_parentDeparts.visibility = View.VISIBLE
            val fatherdepartForSpiner = mutableListOf("")

            for (element in fatherdepart) {
                fatherdepartForSpiner.add("отдел: ${element.departments.full_name} магазин: ${element.shop.name} ")
            }

            viewModel.getItemsForSpinner().observe(
                viewLifecycleOwner,
                { spinnerData ->
                    adapterFatherDept = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        spinnerData
                    )
                    spinner_parentDeparts.adapter = adapterFatherDept
                }
            )

            spinner_parentDeparts.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {

                        parentDepart = if (position > 0) {
                            fatherdepart[position - 1].departments.id
                        } else {
                            null
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }
        }
    }
}
