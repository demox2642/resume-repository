package com.skilbox.shopdb.ProductForSale

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.NewSale

class ProductForSaleAdapter() : AsyncListDifferDelegationAdapter<NewSale>(NewSaleDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(ProductForSaleAdapterDelegate())
    }

    class NewSaleDiffUtilCallBack : DiffUtil.ItemCallback<NewSale>() {
        override fun areItemsTheSame(
            oldItem: NewSale,
            newItem: NewSale
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NewSale,
            newItem: NewSale
        ): Boolean {
            return oldItem == newItem
        }
    }
}
