package com.skilbox.shopdb.positionAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.PositionWhithPlace
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.position_list_item.*

class PositionAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<PositionWhithPlace, PositionWhithPlace, PositionAdapterDelegate.PositionWhithPlaceHolder>() {

    class PositionWhithPlaceHolder(view: View, private val onItemClick: (itemId: Long) -> Unit) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: PositionWhithPlace) {
            value_positionName.text = data.name
            value_positionPlaceName.text = data.plase

            Log.e("PositionAdapter", "I'm hear")
            delete_position.setOnClickListener {
                Log.e("ShopsWithDepartmentsAndParentAdapterDelegate", "bind")
                onItemClick(data.id!!)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: PositionWhithPlace,
        items: MutableList<PositionWhithPlace>,
        position: Int
    ): Boolean {
        return item is PositionWhithPlace
    }

    override fun onCreateViewHolder(parent: ViewGroup): PositionWhithPlaceHolder {
        return PositionWhithPlaceHolder(parent.inflate(R.layout.position_list_item), onItemClick)
    }

    override fun onBindViewHolder(
        item: PositionWhithPlace,
        holder: PositionWhithPlaceHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
