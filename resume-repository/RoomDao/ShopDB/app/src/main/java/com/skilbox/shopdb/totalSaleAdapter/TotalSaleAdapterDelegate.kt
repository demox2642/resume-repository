package com.skilbox.shopdb.totalSaleAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.TotalSale
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sale_list_item.*
import kotlinx.android.synthetic.main.supplirs_price_list_item.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class TotalSaleAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<TotalSale, TotalSale, TotalSaleAdapterDelegate.TotalSaleClassHolder>() {

    class TotalSaleClassHolder(
        view: View,
        private val onItemClick: (itemId: Long) -> Unit
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        private val outputDateFormat =
            DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.systemDefault())

        fun bind(data: TotalSale) {

            value_sale_num.text = data.id.toString()
            value_sale_date.text = outputDateFormat.format(data.date).toString()
            value_product_sale.text = data.count_product.toString()
            value_order_sale.text = data.quantity_price.toString()

            delete_sale_button.setOnClickListener {
                Log.e("AddressesAdapterDelegate", "bind")
                onItemClick(data.id!!)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: TotalSale,
        items: MutableList<TotalSale>,
        position: Int
    ): Boolean {
        return item is TotalSale
    }

    override fun onCreateViewHolder(parent: ViewGroup): TotalSaleClassHolder {
        return TotalSaleClassHolder(
            parent.inflate(R.layout.sale_list_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: TotalSale,
        holder: TotalSaleClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
