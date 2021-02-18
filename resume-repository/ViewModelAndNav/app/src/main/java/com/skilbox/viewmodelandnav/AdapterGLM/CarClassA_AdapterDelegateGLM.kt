package com.skilbox.viewmodelapp.Adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate
import kotlinx.android.synthetic.main.car_class_a.*

class CarClassA_AdapterDelegateGLM(

    val onItemClick: (item: ClickCarItem) -> Unit
) : AbsListItemAdapterDelegate<Cars.A_class, Cars, CarClassA_AdapterDelegateGLM.CarClassAHolderGLM>() {

    class CarClassAHolderGLM(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolderGLM(view, onItemClick) {

        fun bind(cars: Cars.A_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc, id = cars.id)
            sizeTextView.text = "Размеры : " + cars.size
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.A_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassAHolderGLM {
        return CarClassAHolderGLM(parent.inflate(R.layout.car_class_a_glm), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.A_class,
        holder: CarClassAHolderGLM,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
