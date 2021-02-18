package com.skilbox.viewmodelapp.Adapter
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate
import kotlinx.android.synthetic.main.car_class_c.*

class CarClassC_AdapterDelegateSGLM(

    val onItemClick: (item: ClickCarItem) -> Unit
) :
    AbsListItemAdapterDelegate<Cars.C_class, Cars, CarClassC_AdapterDelegateSGLM.CarClassCHolderGLM>() {

    class CarClassCHolderGLM(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolderGLM(view, onItemClick) {

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

    override fun onCreateViewHolder(parent: ViewGroup): CarClassCHolderGLM {
        return CarClassCHolderGLM(parent.inflate(R.layout.car_class_c_sglm), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.C_class,
        holder: CarClassCHolderGLM,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
