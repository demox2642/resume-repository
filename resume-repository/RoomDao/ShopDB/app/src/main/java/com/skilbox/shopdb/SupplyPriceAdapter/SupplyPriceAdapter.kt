package com.skilbox.shopdb.SupplyPriceAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.SuppliersPriceProduct

class SupplyPriceAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<SuppliersPriceProduct>(SuppliersPriceProductDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(SupplyPriceAdapterDelegate(onItemClick))
    }

    class SuppliersPriceProductDiffUtilCallBack : DiffUtil.ItemCallback<SuppliersPriceProduct>() {
        override fun areItemsTheSame(
            oldItem: SuppliersPriceProduct,
            newItem: SuppliersPriceProduct
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SuppliersPriceProduct,
            newItem: SuppliersPriceProduct
        ): Boolean {
            return oldItem == newItem
        }
    }
}
