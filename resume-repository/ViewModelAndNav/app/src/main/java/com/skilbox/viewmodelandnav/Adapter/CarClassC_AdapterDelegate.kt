package com.skilbox.viewmodelapp.Adapter
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate
import kotlinx.android.synthetic.main.car_class_c.*

class CarClassC_AdapterDelegate(

    val onItemClick: (item: ClickCarItem) -> Unit
) :
    AbsListItemAdapterDelegate<Cars.C_class, Cars, CarClassC_AdapterDelegate.CarClassCHolder>() {

    class CarClassCHolder(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.C_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc, id = cars.id)
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
