package com.skilbox.viewmodelapp.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.viewmodelandnav.Cars
import com.skilbox.viewmodelandnav.ClickCarItem

class CarAdapterGLM(

    private val onItemClick: (item: ClickCarItem) -> Unit
) : AsyncListDifferDelegationAdapter<Cars>(CarDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(CarClassA_AdapterDelegateGLM(onItemClick))
            .addDelegate(CarClassB_AdapterDelegateGLM(onItemClick))
            .addDelegate(CarClassC_AdapterDelegateGLM(onItemClick))
            .addDelegate(CarClassD_AdapterDelegateGLM(onItemClick))
            .addDelegate(CarClassE_AdapterDelegateGLM(onItemClick))
            .addDelegate(CarClassJ_AdapterDelegateGLM(onItemClick))
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
