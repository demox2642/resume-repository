package com.skilbox.a17_best_practices.Adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.a17_best_practices.Cars
import com.skilbox.a17_best_practices.R
import com.skilbox.a17_best_practices.inflate
import kotlinx.android.synthetic.main.car_class_a.*

class CarClassE_AdapterDelegate(val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Cars.E_class, Cars, CarClassE_AdapterDelegate.CarClassEHolder>() {

    class CarClassEHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.E_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.A_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassEHolder {
        return CarClassEHolder(parent.inflate(R.layout.car_class_e), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.E_class,
        holder: CarClassEHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
