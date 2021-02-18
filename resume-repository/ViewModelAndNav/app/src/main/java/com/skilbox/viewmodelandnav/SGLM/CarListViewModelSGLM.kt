package com.skilbox.viewmodelandnav.SGLM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.CarsRepository

class CarListViewModelSGLM : ViewModel() {

    private val repository = CarsRepository()

    private val carListLiveData = MutableLiveData<List<Cars>>(
        repository.getCarList()
    )

    val carsLiveDataForFragment: LiveData<List<Cars>>
        get() = carListLiveData

    private val showToastLiveData = MutableLiveData<Unit>()

    val showToast: LiveData<Unit>
        get() = showToastLiveData

    fun addCar(
        newCarClass: String,
        newCarImageLinc: String,
        newCarBrend: String,
        newCarName: String,
        newCarEngineCapacity: String,
        newCarSpecial: String
    ) {

        Log.e("addCar", "List:${carListLiveData.value.orEmpty()}")
        val newCar = repository.generateCar(
            newCarClass, newCarImageLinc, newCarBrend, newCarName, newCarEngineCapacity, newCarSpecial
        )
        val listForUpdate = listOf(newCar) + carListLiveData.value.orEmpty()
        carListLiveData.postValue(listForUpdate)
        Log.e("addCar", "List:$listForUpdate")
    }

    fun deleteCar(position: Int) {

        carListLiveData.postValue(repository.deleteCar(carListLiveData.value.orEmpty(), position))
        showToastLiveData.postValue(Unit)
    }
}
