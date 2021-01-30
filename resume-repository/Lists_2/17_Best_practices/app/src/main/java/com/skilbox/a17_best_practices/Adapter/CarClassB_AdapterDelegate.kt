package com.skilbox.a17_best_practices.Adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.a17_best_practices.Cars
import com.skilbox.a17_best_practices.R
import com.skilbox.a17_best_practices.inflate
import kotlinx.android.synthetic.main.car_class_b.*

class CarClassB_AdapterDelegate(val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Cars.B_class, Cars, CarClassB_AdapterDelegate.CarClassBHolder>() {

    class CarClassBHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.B_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            dorsTextView.text = "Количество дверей : " + cars.doors_quantity
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.B_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassBHolder {
        return CarClassBHolder(parent.inflate(R.layout.car_class_b), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.B_class,
        holder: CarClassBHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
