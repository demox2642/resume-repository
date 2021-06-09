package com.skilbox.shopdb.employerAdapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.shopdb.R
import com.skilbox.shopdb.database.tables.EmploersPositionPlace
import com.skilbox.shopdb.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.position_list_item.*

class EmployerAdapterDelegate(
    val onItemClick: (itemId: Long) -> Unit
) : AbsListItemAdapterDelegate<EmploersPositionPlace, EmploersPositionPlace, EmployerAdapterDelegate.EmploersPositionPlaceHolder>() {

    class EmploersPositionPlaceHolder(view: View, private val onItemClick: (itemId: Long) -> Unit) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: EmploersPositionPlace) {
            value_positionName.text = data.name
            value_positionPlaceName.text = data.plase

            delete_position.setOnClickListener {
                Log.e("ShopsWithDepartmentsAndParentAdapterDelegate", "empId=${data.id}")
                onItemClick(data.id!!)
            }
        }

        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: EmploersPositionPlace,
        items: MutableList<EmploersPositionPlace>,
        position: Int
    ): Boolean {
        return item is EmploersPositionPlace
    }

    override fun onCreateViewHolder(parent: ViewGroup): EmploersPositionPlaceHolder {
        return EmploersPositionPlaceHolder(parent.inflate(R.layout.position_list_item), onItemClick)
    }

    override fun onBindViewHolder(
        item: EmploersPositionPlace,
        holder: EmploersPositionPlaceHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
