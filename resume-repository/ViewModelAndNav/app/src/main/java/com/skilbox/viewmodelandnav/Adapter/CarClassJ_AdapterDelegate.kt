package com.skilbox.viewmodelapp.Adapter
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate

class CarClassJ_AdapterDelegate(

    val onItemClick: (item: ClickCarItem) -> Unit
) :
    AbsListItemAdapterDelegate<Cars.J_class, Cars, CarClassJ_AdapterDelegate.CarClassJHolder>() {

    class CarClassJHolder(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.J_class) {
            bindMainInfo(
                brend = cars.brend,
                name = cars.name,
                engine_capacity = cars.engine_capacity,
                image_linc = cars.image_linc, id = cars.id
            )
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.J_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassJHolder {
        return CarClassJHolder(parent.inflate(R.layout.car_class_j), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.J_class,
        holder: CarClassJHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
