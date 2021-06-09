package com.skilbox.shopdb.departmentsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.shopdb.database.tables.ShopsWithDepartmentsAndParent

class DepartmentsAdapter(
    onItemClick: (item: Long) -> Unit
) : AsyncListDifferDelegationAdapter<ShopsWithDepartmentsAndParent>(
    ShopsWithDepartmentsDiffUtilCallBack()
) {

    init {
        delegatesManager
            .addDelegate(DepartmentsAdapterDelegate(onItemClick))
    }

    class ShopsWithDepartmentsDiffUtilCallBack :
        DiffUtil.ItemCallback<ShopsWithDepartmentsAndParent>() {
        override fun areItemsTheSame(
            oldItem: ShopsWithDepartmentsAndParent,
            newItem: ShopsWithDepartmentsAndParent
        ): Boolean {
            return oldItem.departID == newItem.departID
        }

        override fun areContentsTheSame(
            oldItem: ShopsWithDepartmentsAndParent,
            newItem: ShopsWithDepartmentsAndParent
        ): Boolean {
            return oldItem == newItem
        }
    }
}
