package com.skilbox.shopdb

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.skilbox.shopdb.database.tables.Employers
import com.skilbox.shopdb.vm.DataBaseViewModel
import kotlinx.android.synthetic.main.fragment_add_employer.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

// ktlint-disable no-wildcard-imports

class AddEmployerFragment : Fragment(R.layout.fragment_add_employer) {

    private var surname: String = ""

    private var name: String = ""

    private var middle_name: String = ""

    private var birthday: Instant? = null

    private var position: String? = ""

    private var position_id: Long? = null

    private var dept_id: Long? = null

    private val outputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    private val viewModel: DataBaseViewModel by navGraphViewModels(R.id.app_nav) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        menu_place.visibility = View.GONE

        val calendarBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select employer birthday ")

        val picker: MaterialDatePicker<*> = calendarBuilder.build()

        add_employer_button.setOnClickListener {
            addEmployer()
        }

        value_employer_birthday.setOnClickListener {
            picker.show(childFragmentManager, picker.toString())
        }

        picker.addOnPositiveButtonClickListener {

            birthday = outputDateFormat.parse(outputDateFormat.format(it)).toInstant()
            value_employer_birthday.setText(outputDateFormat.format(it))
        }

        val itemsPositions = viewModel.getPositionList()
        val itemsPlase = mutableListOf<String>()
        val positionWithPlase = viewModel.positionsForFragment.value

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, itemsPositions)

        employer_position.setAdapter(adapter)
        employer_position.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectItem = parent.getItemAtPosition(position).toString()
                if (employer_position.text.isNotEmpty()) {
                    val itemsAfterFilter = positionWithPlase?.filter { it.name == selectItem }
                    for (i in 0 until itemsAfterFilter?.size!!) {
                        itemsPlase.add(itemsAfterFilter[i].plase!!)
                        val placeAdapter =
                            ArrayAdapter(requireContext(), R.layout.list_item, itemsPlase)
                        employer_places.setAdapter(placeAdapter)
                        menu_place.visibility = View.VISIBLE

                        employer_places.onItemClickListener =
                            AdapterView.OnItemClickListener { parent, view, position, id ->

                                val selectItemPlace = parent.getItemAtPosition(position).toString()
                                val departId =
                                    itemsAfterFilter.filter { it.plase == selectItemPlace }

                                position_id = departId[0].id
                                viewModel.getPositionsById(position_id!!)
                            }
                    }
                }
            }
    }

    private fun addEmployer() {

        surname = value_employerSurname.text.toString()

        name = value_employer_middle_name.text.toString()

        middle_name = value_employer_middle_name.text.toString()

        position = employer_position.text.toString()

        dept_id = viewModel.positionAfterFilterForFragment.value?.dept_id

        val employer = Employers(null, surname, name, middle_name, birthday!!)
        viewModel.findEmployer(surname, name, middle_name)

        val emp = viewModel.employerForFragment.value

        viewModel.addEmpAndLincPositionDept(
            employer, dept_id!!, position_id!!,
            LocalDateTime.now().toInstant(
                ZoneOffset.UTC
            )
        )

        findNavController().popBackStack()
    }
}
