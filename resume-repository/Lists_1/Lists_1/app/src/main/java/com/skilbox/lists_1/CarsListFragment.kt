package com.skilbox.lists_1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.cars_list_main.*

class CarsListFragment(
    private var carsList: List<Cars>
) : Fragment(R.layout.cars_list_main) {

    private val addCarDialog: AddCarDialog?
        get() = activity?.let { it as AddCarDialog }

    private val getCarList: GetCarList?
        get() = activity?.let { it as GetCarList }

    private var carAdapter: CarAdapter? = null

    private fun initList() {
        carAdapter = CarAdapter { position -> deleteCar(position) }

        Log.e("addflap", "initList")

        with(carListView) {
            adapter = carAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteCar(position: Int) {

        carsList = carsList.filterIndexed { index, cars -> index != position }

        if (carsList.isEmpty()) {
            cleanListMassage.visibility = View.VISIBLE
        }
        getCarList?.getCarList(carsList)
        carAdapter?.updateCarList(carsList)
        carAdapter?.notifyItemRemoved(position)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retainInstance = true

        initList()

        add_flap.setOnClickListener { addCarDialog?.showSingleDialog() }
        carAdapter?.updateCarList(carsList)
        carAdapter?.notifyDataSetChanged()

        Log.e("addflap", "onActivityCreated")
    }
}
