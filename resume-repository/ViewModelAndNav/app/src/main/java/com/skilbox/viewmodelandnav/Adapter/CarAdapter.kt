package com.skilbox.viewmodelapp.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem

class CarAdapter(

    private val onItemClick: (item: ClickCarItem) -> Unit
) : AsyncListDifferDelegationAdapter<Cars>(CarDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(CarClassA_AdapterDelegate(onItemClick))
            .addDelegate(CarClassB_AdapterDelegate(onItemClick))
            .addDelegate(CarClassC_AdapterDelegate(onItemClick))
            .addDelegate(CarClassD_AdapterDelegate(onItemClick))
            .addDelegate(CarClassE_AdapterDelegate(onItemClick))
            .addDelegate(CarClassJ_AdapterDelegate(onItemClick))
    }

    class CarDiffUtilCallBack : DiffUtil.ItemCallback<Cars>() {
        override fun areItemsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return when {
                oldItem is Cars.A_class && newItem is Cars.A_class -> oldItem.id == newItem.id
                oldItem is Cars.B_class && newItem is Cars.B_class -> oldItem.id == newItem.id
                oldItem is Cars.C_class && newItem is Cars.C_class -> oldItem.id == newItem.id
                oldItem is Cars.D_class && newItem is Cars.D_class -> oldItem.id == newItem.id
                oldItem is Cars.E_class && newItem is Cars.E_class -> oldItem.id == newItem.id
                oldItem is Cars.J_class && newItem is Cars.J_class -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem == newItem
        }
    }
}
