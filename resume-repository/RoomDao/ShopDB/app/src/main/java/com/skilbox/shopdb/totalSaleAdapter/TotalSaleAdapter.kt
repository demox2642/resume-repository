package com.skilbox.shopdb.totalSaleAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.TotalSale

class TotalSaleAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<TotalSale>(TotalSaleDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(TotalSaleAdapterDelegate(onItemClick))
    }

    class TotalSaleDiffUtilCallBack : DiffUtil.ItemCallback<TotalSale>() {
        override fun areItemsTheSame(
            oldItem: TotalSale,
            newItem: TotalSale
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TotalSale,
            newItem: TotalSale
        ): Boolean {
            return oldItem == newItem
        }
    }
}
