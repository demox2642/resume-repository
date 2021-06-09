package com.skilbox.shopdb.shopsAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.ShopsAndAdresses
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.shop_list_item.*

class ShopsAndAdressesAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<ShopsAndAdresses, ShopsAndAdresses, ShopsAndAdressesAdapterDelegate.ShopsAndAdressesClassHolder>() {

    class ShopsAndAdressesClassHolder(view: View, private val onItemClick: (itemId: Long) -> Unit) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(ShopsAndAdresses: ShopsAndAdresses) {
            value_cty.text = ShopsAndAdresses.addresses.city
            value_street.text = ShopsAndAdresses.addresses.street
            value_building.text = ShopsAndAdresses.addresses.building
            shop_name_value.text = ShopsAndAdresses.shops.name

            delete_shop.setOnClickListener {
                Log.e("ShopsAndAdressesAdapterDelegate", "bind")
                onItemClick(ShopsAndAdresses.shops.id!!)
            }
        }


        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: ShopsAndAdresses,
        items: MutableList<ShopsAndAdresses>,
        position: Int
    ): Boolean {
        return item is ShopsAndAdresses
    }

    override fun onCreateViewHolder(parent: ViewGroup): ShopsAndAdressesClassHolder {
        return ShopsAndAdressesClassHolder(parent.inflate(R.layout.shop_list_item), onItemClick)
    }

    override fun onBindViewHolder(
        item: ShopsAndAdresses,
        holder: ShopsAndAdressesClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
