package com.skilbox.shopdb.SuppliyFragmentAdapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.TotalSupply
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.supply_list_item.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class SuppliyFragmentAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<TotalSupply, TotalSupply, SuppliyFragmentAdapterDelegate.TotalSupplyClassHolder>() {

    class TotalSupplyClassHolder(
        view: View,
        private val onItemClick: (itemId: Long) -> Unit
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        private val outputDateFormat =
            DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.systemDefault())

        fun bind(data: TotalSupply) {

            value_supply_num.text = data.id.toString()
            value_quantity_date.text = outputDateFormat.format(data.date).toString()
            value_product_quantity.text = data.price_summ.toString()
            value_order_quantity.text = data.count_product.toString()

            delete_supply.setOnClickListener {
                onItemClick(data.id!!)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: TotalSupply,
        items: MutableList<TotalSupply>,
        position: Int
    ): Boolean {
        return item is TotalSupply
    }

    override fun onCreateViewHolder(parent: ViewGroup): TotalSupplyClassHolder {
        return TotalSupplyClassHolder(
            parent.inflate(R.layout.supply_list_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: TotalSupply,
        holder: TotalSupplyClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
