package com.skilbox.shopdb.SalePriceAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.SaleWhithProductsPrice
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sale_price_list_item.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class SalePriceAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<SaleWhithProductsPrice, SaleWhithProductsPrice, SalePriceAdapterDelegate.SaleWhithProductsPriceClassHolder>() {

    class SaleWhithProductsPriceClassHolder(
        view: View,
        private val onItemClick: (itemId: Long) -> Unit
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        private val outputDateFormat =
            DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.systemDefault())

        fun bind(data: SaleWhithProductsPrice) {
            value_nameProduct.text = data.products.name
            value_price.text = data.salePrice.price.toString()
            value_price_data.text = outputDateFormat.format(data.salePrice.date).toString()

            delete_sale_price.setOnClickListener {
                Log.e("AddressesAdapterDelegate", "bind")
                onItemClick(data.salePrice.id!!)
            }
        }


        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: SaleWhithProductsPrice,
        items: MutableList<SaleWhithProductsPrice>,
        position: Int
    ): Boolean {
        return item is SaleWhithProductsPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup): SaleWhithProductsPriceClassHolder {
        return SaleWhithProductsPriceClassHolder(
            parent.inflate(R.layout.sale_price_list_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: SaleWhithProductsPrice,
        holder: SaleWhithProductsPriceClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
