package com.skillbox.github.ui.repository_list.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.github.data.Project

class RepositoryListAdapter(onItemClick: (position: Int) -> Unit) : AsyncListDifferDelegationAdapter<Project>(
    MovieDiffUtilCallBack()
) {

    init {
        delegatesManager.addDelegate(RepositoryDelegateAdapter(onItemClick))
    }

    class MovieDiffUtilCallBack : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
            Log.e("check movie", "${oldItem == newItem}")
            return oldItem == newItem
        }
    }
}
