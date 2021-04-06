package com.skilbox.networking.data.network.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skilbox.jsonandretrofit.R
import com.skilbox.jsonandretrofit.data.MovieReiting
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_vie_for_list.*
import kotlinx.android.synthetic.main.movie_vie_for_list.view.*

abstract class BaseHolder(
    view: View,
    private val like: Boolean,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {
    protected fun bindMainInfo(
        id: String,
        title: String,
        year: String,
        ganre: String?,
        poster: String,
        reiting: MovieReiting,
        scores: Map<String, String>
    ) {

        like_image.setOnClickListener {
            onItemClick(adapterPosition)
        }

        if (like) {
            like_image.setImageResource(R.drawable.dizlike)
        } else { like_image.setImageResource(R.drawable.like) }

        movie_title.text = title
        movie_year.text = year
        movie_id.text = id
        movie_type.text = ganre
        movie_reiting.text = reiting.toString()
        scores_text.text = scores.toString().replace(",", "\n").replace("{", "").replace("}", "")

        Glide.with(movie_image)
            .load(poster)
            .placeholder(R.drawable.load_imege)
            .into(itemView.movie_image)
            .view
    }
}
