package com.skilbox.shopdb.shopsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.ShopsAndAdresses


class ShopsAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<ShopsAndAdresses>(ShopsDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(ShopsAndAdressesAdapterDelegate(onItemClick))
    }

    class ShopsDiffUtilCallBack : DiffUtil.ItemCallback<ShopsAndAdresses>() {
        override fun areItemsTheSame(
            oldItem: ShopsAndAdresses,
            newItem: ShopsAndAdresses
        ): Boolean {
            return oldItem.shops.id == newItem.shops.id
        }

        override fun areContentsTheSame(
            oldItem: ShopsAndAdresses,
            newItem: ShopsAndAdresses
        ): Boolean {
            return oldItem == newItem
        }
    }
}
