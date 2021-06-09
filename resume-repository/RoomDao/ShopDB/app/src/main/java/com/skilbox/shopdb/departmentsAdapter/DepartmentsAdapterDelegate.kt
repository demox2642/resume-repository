package com.skilbox.shopdb.departmentsAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.ShopsWithDepartmentsAndParent
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.depart_list_item.*

class DepartmentsAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<ShopsWithDepartmentsAndParent, ShopsWithDepartmentsAndParent, DepartmentsAdapterDelegate.ShopsWithDepartmentsAndParentClassHolder>() {

    class ShopsWithDepartmentsAndParentClassHolder(
        view: View,
        private val onItemClick: (itemId: Long) -> Unit
    ) : RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: ShopsWithDepartmentsAndParent) {
            value_DepartName.text = data.full_name
            value_fatherDepart.text = data.parentName
            value_shopName.text = data.shopName

            delete_depart.setOnClickListener {
                Log.e("ShopsWithDepartmentsAndParentAdapterDelegate", "bind")
                onItemClick(data.departID)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: ShopsWithDepartmentsAndParent,
        items: MutableList<ShopsWithDepartmentsAndParent>,
        position: Int
    ): Boolean {
        return item is ShopsWithDepartmentsAndParent
    }

    override fun onCreateViewHolder(parent: ViewGroup): ShopsWithDepartmentsAndParentClassHolder {
        return ShopsWithDepartmentsAndParentClassHolder(
            parent.inflate(R.layout.depart_list_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: ShopsWithDepartmentsAndParent,
        holder: ShopsWithDepartmentsAndParentClassHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
