package com.skilbox.shopdb.productsAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.ProductsAndUnit
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.product_list_item.*

class ProductsAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<ProductsAndUnit, ProductsAndUnit, ProductsAdapterDelegate.Products_UnitWhithProductsClassHolder>() {

    class Products_UnitWhithProductsClassHolder(
        view: View,
        private val onItemClick: (itemId: Long) -> Unit
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: ProductsAndUnit) {
            value_productName.text = data.products.name
            value_productUNIT.text = data.productsUnit.name

            delete_supply.setOnClickListener {
                Log.e("AddressesAdapterDelegate", "bind")
                onItemClick(data.products.id!!)
            }
        }


        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: ProductsAndUnit,
        items: MutableList<ProductsAndUnit>,
        position: Int
    ): Boolean {
        return item is ProductsAndUnit
    }

    override fun onCreateViewHolder(parent: ViewGroup): Products_UnitWhithProductsClassHolder {
        return Products_UnitWhithProductsClassHolder(
            parent.inflate(R.layout.product_list_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: ProductsAndUnit,
        holder: Products_UnitWhithProductsClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
