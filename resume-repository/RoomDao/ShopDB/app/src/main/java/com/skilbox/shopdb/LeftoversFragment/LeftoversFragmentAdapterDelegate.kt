package com.skilbox.shopdb.LeftoversFragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.CountsProductsInWarehouse
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.products_in_warehouse_list_item.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LeftoversFragmentAdapterDelegate() : AbsListItemAdapterDelegate<CountsProductsInWarehouse, CountsProductsInWarehouse, LeftoversFragmentAdapterDelegate.CountsProductsInWarehouseClassHolder>() {

    class CountsProductsInWarehouseClassHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        private val outputDateFormat =
            DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.systemDefault())

        fun bind(data: CountsProductsInWarehouse) {
            value_productName.text = data.product_name
            value_product_count.text = data.count.toString()
            value_update_date.text = outputDateFormat.format(data.change_date).toString()
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: CountsProductsInWarehouse,
        items: MutableList<CountsProductsInWarehouse>,
        position: Int
    ): Boolean {
        return item is CountsProductsInWarehouse
    }

    override fun onCreateViewHolder(parent: ViewGroup): CountsProductsInWarehouseClassHolder {
        return CountsProductsInWarehouseClassHolder(
            parent.inflate(R.layout.products_in_warehouse_list_item)
        )
    }

    override fun onBindViewHolder(
        item: CountsProductsInWarehouse,
        holder: CountsProductsInWarehouseClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
