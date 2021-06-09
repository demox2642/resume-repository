package com.skilbox.shopdb.ProductForSale

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.NewSale
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.p_c_s_list_item.*

class ProductForSaleAdapterDelegate() : AbsListItemAdapterDelegate<NewSale, NewSale, ProductForSaleAdapterDelegate.NewSalePlaceHolder>() {

    class NewSalePlaceHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: NewSale) {

            value_nameProduct_add_supply.text = data.product_name
            value_price_data_add_supply.text = (data.count_product * data.price).toString()
            value_add_supply_name.text = data.count_product.toString()
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: NewSale,
        items: MutableList<NewSale>,
        position: Int
    ): Boolean {
        return item is NewSale
    }

    override fun onCreateViewHolder(parent: ViewGroup): NewSalePlaceHolder {
        return NewSalePlaceHolder(parent.inflate(R.layout.product_for_sale_list_item))
    }

    override fun onBindViewHolder(
        item: NewSale,
        holder: NewSalePlaceHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
