package com.skilbox.viewmodelandnav.GLM

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelapp.Adapter.CarAdapterGLM
import kotlinx.android.synthetic.main.fragment_grid_layout_manager.*
import kotlinx.android.synthetic.main.fragment_grid_layout_manager.add_flap

class GridLayoutManagerFragment : Fragment(R.layout.fragment_grid_layout_manager) {

    private var carAdapter: CarAdapterGLM? = null

    private val carListViewModel: CarListViewModelGLM by navGraphViewModels(R.id.GLM) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retainInstance = true

        initList()

        observViewModelState()
        add_flap.setOnClickListener {
            Log.e("LLMF", "Click button add_flip")
            showSingleDialog()
        }
    }

    private fun initList() {
        carAdapter = CarAdapterGLM { ClickCarItem -> clickHandler(ClickCarItem) }

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
        }
    }

    private fun clickHandler(item: ClickCarItem) {
        when (item.itLongClick) {
            true -> deleteItem(item.id.toInt())
            false -> detailInfo(item.id, item.carImage, item.carBrend, item.carName, item.carEngine_capacity)
        }
    }

    private fun detailInfo(id: Long, carImage: String, carBrend: String, carName: String, carEngine_capacity: String) {
        Log.e("LLManager", "I'am in detailInfo id=$id")

        val action = GridLayoutManagerFragmentDirections.actionGLMFragmentToDetailFragmentGLM(id, carImage, carBrend, carName, carEngine_capacity)
        findNavController().navigate(action)

        Log.e("LLManager", "I'am in detailInfo id=$id")
    }

    private fun deleteItem(position: Int) {
        carListViewModel.deleteCar(position)
    }

    private fun showSingleDialog() {
        val carClass: Array<String> = arrayOf("Класс A", "Класс B", "Класс C", "Класс D", "Класс E", "Класс J")
        AlertDialog.Builder(requireContext())
            .setTitle("Выберите класс автомобиля:")
            .setItems(carClass) { _, wich -> showDialogForAddCar(carClass[wich]) }
            .setPositiveButton("Ok", null)
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun showDialogForAddCar(carClass: String) {
        val args = GridLayoutManagerFragmentDirections.actionGLMFragmentToCarClassDialogFragmentGLM(carClass = carClass)
        findNavController().navigate(args)
    }

    private fun observViewModelState() {
        carListViewModel.carsLiveDataForFragment
            .observe(viewLifecycleOwner) { newCar -> carAdapter?.items = newCar }

        carListViewModel.showToast
            .observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Элемент удален", Toast.LENGTH_LONG).show()
            }
    }

    override fun onResume() {
        super.onResume()
        view?.clearFocus()
    }
}
