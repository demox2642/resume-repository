package com.skilbox.shopdb.positionAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.PositionWhithPlace

class PositionAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<PositionWhithPlace>(PositionWhithPlaceDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(PositionAdapterDelegate(onItemClick))
    }

    class PositionWhithPlaceDiffUtilCallBack : DiffUtil.ItemCallback<PositionWhithPlace>() {
        override fun areItemsTheSame(
            oldItem: PositionWhithPlace,
            newItem: PositionWhithPlace
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PositionWhithPlace,
            newItem: PositionWhithPlace
        ): Boolean {
            return oldItem == newItem
        }
    }
}
