package com.skilbox.a17_best_practices.Adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skilbox.a17_best_practices.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.car_class_a.*

abstract class BaseCarHolder(

    view: View,
    private val onItemClick: (position: Int) -> Unit

) : RecyclerView.ViewHolder(view), LayoutContainer {

    init {
        Log.e("BaseCarHolder1", "init")
        view.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    protected fun bindMainInfo(
        image_linc: String,
        brend: String,
        name: String,
        engine_capacity: String
    ) {
        Log.e("BaseCarHolder2", "init")

        car_brend.text = brend
        car_name.text = name
        engine_capacity_textview.text = "Объем двигателя : $engine_capacity"

        Log.e("BaseCarHolder3", "bind + $brend")

        Glide.with(itemView)
            .load(image_linc)
            .placeholder(R.drawable.load_imege)
            .into(car_imege)
    }
}
