package com.skilbox.shopdb.suppliersAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.SuppliersAndAdresses
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.supplirs_list_item.*

class SuppliersAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<SuppliersAndAdresses, SuppliersAndAdresses, SuppliersAdapterDelegate.Products_UnitWhithProductsClassHolder>() {

    class Products_UnitWhithProductsClassHolder(
        view: View,
        private val onItemClick: (itemId: Long) -> Unit
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: SuppliersAndAdresses) {
            value_cty.text = data.addresses.city
            value_street.text = data.addresses.street
            value_building.text = data.addresses.building
            suppliers_name_value.text = data.suppliers.name

            delete_suppliers.setOnClickListener {
                Log.e("AddressesAdapterDelegate", "bind")
                onItemClick(data.suppliers.id!!)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: SuppliersAndAdresses,
        items: MutableList<SuppliersAndAdresses>,
        position: Int
    ): Boolean {
        return item is SuppliersAndAdresses
    }

    override fun onCreateViewHolder(parent: ViewGroup): Products_UnitWhithProductsClassHolder {
        return Products_UnitWhithProductsClassHolder(
            parent.inflate(R.layout.supplirs_list_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: SuppliersAndAdresses,
        holder: Products_UnitWhithProductsClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
