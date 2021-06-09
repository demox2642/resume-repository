package com.skilbox.shopdb.SalePriceAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.SaleWhithProductsPrice


class SalePriceAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<SaleWhithProductsPrice>(
    Products_UnitWhithProductsDiffUtilCallBack()
) {

    init {
        delegatesManager
            .addDelegate(SalePriceAdapterDelegate(onItemClick))
    }

    class Products_UnitWhithProductsDiffUtilCallBack :
        DiffUtil.ItemCallback<SaleWhithProductsPrice>() {
        override fun areItemsTheSame(
            oldItem: SaleWhithProductsPrice,
            newItem: SaleWhithProductsPrice
        ): Boolean {
            return oldItem.salePrice.id == newItem.salePrice.id
        }

        override fun areContentsTheSame(
            oldItem: SaleWhithProductsPrice,
            newItem: SaleWhithProductsPrice
        ): Boolean {
            return oldItem == newItem
        }
    }
}
