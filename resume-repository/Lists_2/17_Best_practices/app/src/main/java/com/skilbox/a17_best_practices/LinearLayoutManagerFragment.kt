package com.skilbox.a17_best_practices

import AddCarDialog
import GetCarList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.a17_best_practices.Adapter.CarAdapter
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator
import kotlinx.android.synthetic.main.fragment_linear_layout_manager.*

class LinearLayoutManagerFragment(
    private var carsList: List<Cars>
) : Fragment(R.layout.fragment_linear_layout_manager) {

    private val addCarDialog: AddCarDialog?
        get() = activity?.let { it as AddCarDialog }

    private val getCarList: GetCarList?
        get() = activity?.let { it as GetCarList }

    private var carAdapter: CarAdapter? = null

    private fun initList() {
        carAdapter = CarAdapter { position -> deleteCar(position) }

        Log.e("addflap", "initList")

        with(llm_carList_view) {
            adapter = carAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInTopAnimator()
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
