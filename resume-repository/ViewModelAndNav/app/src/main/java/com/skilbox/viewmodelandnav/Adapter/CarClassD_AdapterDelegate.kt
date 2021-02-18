package com.skilbox.viewmodelapp.Adapter
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate
import kotlinx.android.synthetic.main.car_class_d.*

class CarClassD_AdapterDelegate(

    val onItemClick: (item: ClickCarItem) -> Unit
) : AbsListItemAdapterDelegate<Cars.D_class, Cars, CarClassD_AdapterDelegate.CarClassDHolder>() {

    class CarClassDHolder(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.D_class) {
            bindMainInfo(
                brend = cars.brend,
                name = cars.name,
                engine_capacity = cars.engine_capacity,
                image_linc = cars.image_linc, id = cars.id
            )
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
