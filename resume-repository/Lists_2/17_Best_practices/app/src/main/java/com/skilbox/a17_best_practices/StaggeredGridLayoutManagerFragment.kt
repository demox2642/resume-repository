package com.skilbox.a17_best_practices

import AddCarDialog
import GetCarList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.skilbox.a17_best_practices.Adapter.CarAdapter
import kotlinx.android.synthetic.main.fragment_linear_layout_manager.add_flap
import kotlinx.android.synthetic.main.fragment_linear_layout_manager.cleanListMassage
import kotlinx.android.synthetic.main.fragment_staggered_grid_layout_manager.*
import java.util.concurrent.Executors


class StaggeredGridLayoutManagerFragment(
    private var carsList: List<Cars>
) : Fragment(R.layout.fragment_staggered_grid_layout_manager) {

    private val addCarDialog: AddCarDialog?
        get() = activity?.let { it as AddCarDialog }

    private val getCarList: GetCarList?
        get() = activity?.let { it as GetCarList }

    private var carAdapter: CarAdapter? = null

//    var pagedList: PagedList<Cars> = PagedList.Config(config)
//        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
//        .build()
//
//    var config = PagedList.Config.Builder()
//        .setEnablePlaceholders(false)
//        .setPageSize(10)
//        .build()

    private fun initList() {
        carAdapter = CarAdapter { position -> deleteCar(position) }

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )

        Log.e("addflap", "initList")

        with(sglm_carList_view) {
            adapter = carAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            addItemDecoration(ItemOffsetDecoration(context))
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
        //  carAdapter?.notifyItemRemoved(position)
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
