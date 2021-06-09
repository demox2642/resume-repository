package com.skilbox.shopdb.LeftoversFragment

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.CountsProductsInWarehouse

class LeftoversFragmentAdapter() : AsyncListDifferDelegationAdapter<CountsProductsInWarehouse>(
    ProductsInWarehouseDiffUtilCallBack()
) {

    init {
        delegatesManager
            .addDelegate(LeftoversFragmentAdapterDelegate())
    }

    class ProductsInWarehouseDiffUtilCallBack :
        DiffUtil.ItemCallback<CountsProductsInWarehouse>() {
        override fun areItemsTheSame(
            oldItem: CountsProductsInWarehouse,
            newItem: CountsProductsInWarehouse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CountsProductsInWarehouse,
            newItem: CountsProductsInWarehouse
        ): Boolean {
            return oldItem == newItem
        }
    }
}
