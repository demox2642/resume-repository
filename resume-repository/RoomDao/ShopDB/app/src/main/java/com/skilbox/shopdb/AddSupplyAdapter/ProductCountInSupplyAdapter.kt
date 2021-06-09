package com.skilbox.shopdb.AddSupplyAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.ProductCountInSupply

class ProductCountInSupplyAdapter(
) : AsyncListDifferDelegationAdapter<ProductCountInSupply>(SuppliersPriceProductDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(ProductCountInSupplyAdapterDelegate())
    }

    class SuppliersPriceProductDiffUtilCallBack : DiffUtil.ItemCallback<ProductCountInSupply>() {
        override fun areItemsTheSame(
            oldItem: ProductCountInSupply,
            newItem: ProductCountInSupply
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductCountInSupply,
            newItem: ProductCountInSupply
        ): Boolean {
            return oldItem == newItem
        }
    }
}
