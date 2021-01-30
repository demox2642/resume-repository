package com.skilbox.a17_best_practices.Adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.a17_best_practices.Cars
import com.skilbox.a17_best_practices.R
import com.skilbox.a17_best_practices.inflate

class CarClassJ_AdapterDelegate(val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Cars.J_class, Cars, CarClassJ_AdapterDelegate.CarClassJHolder>() {

    class CarClassJHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.J_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.J_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassJHolder {
        return CarClassJHolder(parent.inflate(R.layout.car_class_e), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.J_class,
        holder: CarClassJHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
