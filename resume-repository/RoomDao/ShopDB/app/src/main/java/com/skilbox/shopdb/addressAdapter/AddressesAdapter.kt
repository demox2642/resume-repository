package com.skilbox.shopdb.addressAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.Addresses

class AddressesAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<Addresses>(AddressesDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(AddressesAdapterDelegate(onItemClick))
    }

    class AddressesDiffUtilCallBack : DiffUtil.ItemCallback<Addresses>() {
        override fun areItemsTheSame(oldItem: Addresses, newItem: Addresses): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Addresses, newItem: Addresses): Boolean {
            return oldItem == newItem
        }
    }
}
