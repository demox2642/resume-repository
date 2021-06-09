package com.skilbox.shopdb.employerAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.EmploersPositionPlace

class EmployerAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<EmploersPositionPlace>(EmploersPositionPlaceDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(EmployerAdapterDelegate(onItemClick))
    }

    class EmploersPositionPlaceDiffUtilCallBack : DiffUtil.ItemCallback<EmploersPositionPlace>() {
        override fun areItemsTheSame(
            oldItem: EmploersPositionPlace,
            newItem: EmploersPositionPlace
        ): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EmploersPositionPlace,
            newItem: EmploersPositionPlace
        ): Boolean {
            return oldItem == newItem
        }
    }
}
