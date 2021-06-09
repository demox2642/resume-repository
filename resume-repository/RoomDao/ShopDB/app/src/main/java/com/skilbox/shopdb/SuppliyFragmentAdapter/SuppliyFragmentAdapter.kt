package com.skilbox.shopdb.SuppliyFragmentAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.TotalSupply

class SuppliyFragmentAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<TotalSupply>(SupplyDetailAndPriceDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(SuppliyFragmentAdapterDelegate(onItemClick))
    }

    class SupplyDetailAndPriceDiffUtilCallBack : DiffUtil.ItemCallback<TotalSupply>() {
        override fun areItemsTheSame(
            oldItem: TotalSupply,
            newItem: TotalSupply
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TotalSupply,
            newItem: TotalSupply
        ): Boolean {
            return oldItem == newItem
        }
    }
}
