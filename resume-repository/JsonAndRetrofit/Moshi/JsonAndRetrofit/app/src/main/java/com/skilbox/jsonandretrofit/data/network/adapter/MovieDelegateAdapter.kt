package com.skilbox.networking.data.network.adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.jsonandretrofit.R
import com.skilbox.networking.data.RemoteMovie

class MovieDelegateAdapter(val like: Boolean, val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<RemoteMovie, RemoteMovie, MovieDelegateAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.movie_vie_for_list), like, onItemClick)
    }

    class MovieViewHolder(view: View, like: Boolean, onItemClick: (position: Int) -> Unit) : BaseHolder(view, like, onItemClick) {

        fun bind(movie: RemoteMovie) {
            bindMainInfo(id = movie.id, title = movie.title, year = movie.year, ganre = movie.ganre, poster = movie.poster.toString(), reiting = movie.reiting, scores = movie.scores)
        }
        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: RemoteMovie,
        items: MutableList<RemoteMovie>,
        position: Int
    ): Boolean {
        return items[position] is RemoteMovie
    }

    override fun onBindViewHolder(
        item: RemoteMovie,
        holder: MovieViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
