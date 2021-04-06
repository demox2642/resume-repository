package com.skilbox.networking.data.network.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skilbox.networking.data.RemoteMovie

class MovieListAdapter(like: Boolean, onItemClick: (position: Int) -> Unit) : AsyncListDifferDelegationAdapter<RemoteMovie>(MovieDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(MovieDelegateAdapter(like, onItemClick))
    }

    class MovieDiffUtilCallBack : DiffUtil.ItemCallback<RemoteMovie>() {
        override fun areItemsTheSame(oldItem: RemoteMovie, newItem: RemoteMovie): Boolean {
            Log.e("check movie", "${oldItem.id == newItem.id}")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RemoteMovie, newItem: RemoteMovie): Boolean {
            Log.e("check movie", "${oldItem == newItem}")
            return oldItem == newItem
        }
    }
}
