package com.skilbox.shopdb.suppliersAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.SuppliersAndAdresses

class SuppliersAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<SuppliersAndAdresses>(
    Products_UnitWhithProductsDiffUtilCallBack()
) {

    init {
        delegatesManager
            .addDelegate(SuppliersAdapterDelegate(onItemClick))
    }

    class Products_UnitWhithProductsDiffUtilCallBack :
        DiffUtil.ItemCallback<SuppliersAndAdresses>() {
        override fun areItemsTheSame(
            oldItem: SuppliersAndAdresses,
            newItem: SuppliersAndAdresses
        ): Boolean {
            return oldItem.suppliers.id == newItem.suppliers.id
        }

        override fun areContentsTheSame(
            oldItem: SuppliersAndAdresses,
            newItem: SuppliersAndAdresses
        ): Boolean {
            return oldItem == newItem
        }
    }
}
