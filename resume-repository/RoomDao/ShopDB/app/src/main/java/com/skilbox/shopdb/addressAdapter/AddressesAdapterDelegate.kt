package com.skilbox.shopdb.addressAdapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.Addresses
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.address_list_item.*

class AddressesAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<Addresses, Addresses, AddressesAdapterDelegate.AddressesClassHolder>() {

    class AddressesClassHolder(view: View, private val onItemClick: (itemId: Long) -> Unit) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(address: Addresses) {
            value_cty.text = address.city
            value_street.text = address.street
            value_building.text = address.building

            delete_addresses.setOnClickListener {
                onItemClick(address.id!!)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: Addresses,
        items: MutableList<Addresses>,
        position: Int
    ): Boolean {
        return item is Addresses
    }

    override fun onCreateViewHolder(parent: ViewGroup): AddressesClassHolder {
        return AddressesClassHolder(parent.inflate(R.layout.address_list_item), onItemClick)
    }

    override fun onBindViewHolder(
        item: Addresses,
        holder: AddressesClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
