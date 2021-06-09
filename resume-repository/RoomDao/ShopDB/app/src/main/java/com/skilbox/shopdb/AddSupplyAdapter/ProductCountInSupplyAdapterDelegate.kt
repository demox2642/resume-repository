package com.skilbox.shopdb.AddSupplyAdapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.ProductCountInSupply
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.p_c_s_list_item.*

class ProductCountInSupplyAdapterDelegate() : AbsListItemAdapterDelegate<ProductCountInSupply, ProductCountInSupply, ProductCountInSupplyAdapterDelegate.ProductCountInSupplyPlaceHolder>() {

    class ProductCountInSupplyPlaceHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: ProductCountInSupply) {

            value_nameProduct_add_supply.text = data.product_name
            value_price_add_supply.text = data.suplilers_name
            value_price_data_add_supply.text = (data.price * data.product_quantity.toBigDecimal()).toString()
            value_add_supply_name.text = data.product_quantity.toString()
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: ProductCountInSupply,
        items: MutableList<ProductCountInSupply>,
        position: Int
    ): Boolean {
        return item is ProductCountInSupply
    }

    override fun onCreateViewHolder(parent: ViewGroup): ProductCountInSupplyPlaceHolder {
        return ProductCountInSupplyPlaceHolder(parent.inflate(R.layout.p_c_s_list_item))
    }

    override fun onBindViewHolder(
        item: ProductCountInSupply,
        holder: ProductCountInSupplyPlaceHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
