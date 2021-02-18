package com.skilbox.viewmodelapp.Adapter
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skilbox.viewmodelandnav.ClickCarItem
import com.skilbox.viewmodelandnav.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.car_class_a.*
import kotlinx.android.synthetic.main.car_class_a.view.*

abstract class BaseCarHolderGLM(

    view: View,

    private val onItemClick: (item: ClickCarItem) -> Unit,
    private var carId: Long? = null,
    private var carImage: String? = null,
    private var carBrend: String? = null,
    private var carName: String? = null,
    private var carEngine_capacity: String? = null
) : RecyclerView.ViewHolder(view), LayoutContainer {

    init {
        Log.e("BaseCarHolder1", "init")

        view.setOnClickListener {

            Log.e("I CLICK", "setOnClickListener")
            onItemClick(
                ClickCarItem(
                    false,
                    carId!!,
                    carImage!!,
                    carBrend!!,
                    carName!!,
                    carEngine_capacity!!
                )

            )
        }

        view.setOnLongClickListener {
            onItemClick(ClickCarItem(true, position.toLong(), carImage!!, carBrend!!, carName!!, carEngine_capacity!!))
            true
        }
    }

    protected fun bindMainInfo(
        id: Long,
        image_linc: String,
        brend: String,
        name: String,
        engine_capacity: String
    ) {
        Log.e("BaseCarHolder2", "init")
        carId = id
        carImage = image_linc
        carBrend = brend
        carName = name
        carEngine_capacity = engine_capacity

        car_brend.text = brend
        car_name.text = name
        engine_capacity_textview.text = "Объем двигателя : $engine_capacity"

        Log.e("BaseCarHolder3", "bind + $brend")

        Glide.with(itemView)
            .load(image_linc)
            .placeholder(R.drawable.load_imege)
            .into(itemView.car_imege)
            .view
    }
}
