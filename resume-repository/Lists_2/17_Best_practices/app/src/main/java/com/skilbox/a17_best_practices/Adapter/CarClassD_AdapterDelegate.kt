package com.skilbox.a17_best_practices.Adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.a17_best_practices.Cars
import com.skilbox.a17_best_practices.R
import com.skilbox.a17_best_practices.inflate
import kotlinx.android.synthetic.main.car_class_b.*
import kotlinx.android.synthetic.main.car_class_d.*

class CarClassD_AdapterDelegate(val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Cars.D_class, Cars, CarClassD_AdapterDelegate.CarClassDHolder>() {

    class CarClassDHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.D_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            seat_quantity_TextView.text = "Количество мест : " + cars.seat_quantity
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.D_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassDHolder {
        return CarClassDHolder(parent.inflate(R.layout.car_class_d), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.D_class,
        holder: CarClassDHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
