package com.skilbox.a17_best_practices

import AddCarDialog
import GetCarList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.skilbox.a17_best_practices.Adapter.CarAdapter
import kotlinx.android.synthetic.main.fragment_grid_layout_manager.*
import kotlinx.android.synthetic.main.fragment_linear_layout_manager.add_flap
import kotlinx.android.synthetic.main.fragment_linear_layout_manager.cleanListMassage

class GridLayoutManagerFragment(
    private var carsList: List<Cars>
) : Fragment(R.layout.fragment_grid_layout_manager) {

    private val addCarDialog: AddCarDialog?
        get() = activity?.let { it as AddCarDialog }

    private val getCarList: GetCarList?
        get() = activity?.let { it as GetCarList }

    private var carAdapter: CarAdapter? = null

    private fun initList() {
        carAdapter = CarAdapter { position -> deleteCar(position) }

        Log.e("addflap", "initList")

        with(glm_carList_view) {
            adapter = carAdapter
            layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.HORIZONTAL, false).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 3 == 0) 2 else 1
                    }
                }
            }
            setHasFixedSize(true)
        }
    }

    private fun deleteCar(position: Int) {

        carsList = carsList.filterIndexed { index, cars -> index != position }

        if (carsList.isEmpty()) {
            cleanListMassage.visibility = View.VISIBLE
        }
        getCarList?.getCarList(carsList)
        carAdapter?.items = carsList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retainInstance = true

        initList()

        add_flap.setOnClickListener { addCarDialog?.showSingleDialog() }
        carAdapter?.items = carsList

        Log.e("addflap", "onActivityCreated")
    }
}
