package com.skilbox.shopdb.productsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.ProductsAndUnit


class ProductsAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<ProductsAndUnit>(Products_UnitWhithProductsDiffUtilCallBack()) {

    init {
        delegatesManager
            .addDelegate(ProductsAdapterDelegate(onItemClick))
    }

    class Products_UnitWhithProductsDiffUtilCallBack : DiffUtil.ItemCallback<ProductsAndUnit>() {
        override fun areItemsTheSame(oldItem: ProductsAndUnit, newItem: ProductsAndUnit): Boolean {
            return oldItem.products.id == newItem.products.id
        }

        override fun areContentsTheSame(
            oldItem: ProductsAndUnit,
            newItem: ProductsAndUnit
        ): Boolean {
            return oldItem == newItem
        }
    }
}
