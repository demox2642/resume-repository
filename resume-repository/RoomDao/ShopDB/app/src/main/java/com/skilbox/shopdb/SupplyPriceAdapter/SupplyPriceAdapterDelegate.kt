package com.skilbox.shopdb.SupplyPriceAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.SuppliersPriceProduct
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.supplirs_price_list_item.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class SupplyPriceAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<SuppliersPriceProduct, SuppliersPriceProduct, SupplyPriceAdapterDelegate.SuppliersPriceProductClassHolder>() {

    class SuppliersPriceProductClassHolder(
        view: View,
        private val onItemClick: (itemId: Long) -> Unit
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        private val outputDateFormat =
            DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.systemDefault())

        fun bind(data: SuppliersPriceProduct) {

            value_nameProduct_supply.text = data.product_name

            value_price_supply.text = data.price.toString()

            value_price_data_supply.text = outputDateFormat.format(data.date).toString()

            value_supply_name.text = data.suplilers_name

            delete_supply_price.setOnClickListener {
                Log.e("AddressesAdapterDelegate", "bind")
                onItemClick(data.id!!)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: SuppliersPriceProduct,
        items: MutableList<SuppliersPriceProduct>,
        position: Int
    ): Boolean {
        return item is SuppliersPriceProduct
    }

    override fun onCreateViewHolder(parent: ViewGroup): SuppliersPriceProductClassHolder {
        return SuppliersPriceProductClassHolder(
            parent.inflate(R.layout.supplirs_price_list_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: SuppliersPriceProduct,
        holder: SuppliersPriceProductClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
