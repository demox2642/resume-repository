package com.skilbox.viewmodelapp.Adapter
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate

class CarClassE_AdapterDelegate(

    val onItemClick: (item: ClickCarItem) -> Unit
) : AbsListItemAdapterDelegate<Cars.E_class, Cars, CarClassE_AdapterDelegate.CarClassEHolder>() {

    class CarClassEHolder(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.E_class) {
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
        return item is Cars.E_class
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
