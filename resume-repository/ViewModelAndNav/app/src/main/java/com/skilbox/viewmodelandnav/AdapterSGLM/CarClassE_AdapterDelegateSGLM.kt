package com.skilbox.viewmodelapp.Adapter
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate

class CarClassE_AdapterDelegateSGLM(

    val onItemClick: (item: ClickCarItem) -> Unit
) : AbsListItemAdapterDelegate<Cars.E_class, Cars, CarClassE_AdapterDelegateSGLM.CarClassEHolderGLM>() {

    class CarClassEHolderGLM(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolderGLM(view, onItemClick) {

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

    override fun onCreateViewHolder(parent: ViewGroup): CarClassEHolderGLM {
        return CarClassEHolderGLM(parent.inflate(R.layout.car_class_e_sglm), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.E_class,
        holder: CarClassEHolderGLM,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
