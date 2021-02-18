package com.skilbox.viewmodelapp.Adapter
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import com.skilbox.viewmodelandnav.inflate
import kotlinx.android.synthetic.main.car_class_b.*

class CarClassB_AdapterDelegateGLM(

    val onItemClick: (item: ClickCarItem) -> Unit
) :
    AbsListItemAdapterDelegate<Cars.B_class, Cars, CarClassB_AdapterDelegateGLM.CarClassBHolderGLM>() {

    class CarClassBHolderGLM(
        view: View,

        onItemClick: (item: ClickCarItem) -> Unit
    ) : BaseCarHolderGLM(view, onItemClick) {

        fun bind(cars: Cars.B_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc, id = cars.id)
            dorsTextView.text = "Количество дверей : " + cars.doors_quantity
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(item: Cars, items: MutableList<Cars>, position: Int): Boolean {
        return item is Cars.B_class
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarClassBHolderGLM {
        return CarClassBHolderGLM(parent.inflate(R.layout.car_class_b_glm), onItemClick)
    }

    override fun onBindViewHolder(
        item: Cars.B_class,
        holder: CarClassBHolderGLM,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
