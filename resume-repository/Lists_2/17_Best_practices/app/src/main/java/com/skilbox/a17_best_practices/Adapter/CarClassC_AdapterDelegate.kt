package com.skilbox.a17_best_practices.Adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.a17_best_practices.Cars
import com.skilbox.a17_best_practices.R
import com.skilbox.a17_best_practices.inflate
import kotlinx.android.synthetic.main.car_class_b.*
import kotlinx.android.synthetic.main.car_class_c.*

class CarClassC_AdapterDelegate(val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Cars.C_class, Cars, CarClassC_AdapterDelegate.CarClassCHolder>() {

    class CarClassCHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.C_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            fuel_consumption_TextView.text = "Расход топлива: " + cars.fuel_consumption
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.C_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassCHolder {
        return CarClassCHolder(parent.inflate(R.layout.car_class_c), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.C_class,
        holder: CarClassCHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
