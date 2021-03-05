package com.skillbox.multithreading.threading.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.multithreading.R
import com.skillbox.multithreading.networking.Movie
import kotlinx.android.synthetic.main.movie_vie_for_list.view.*

class MovieAdapter() : androidx.recyclerview.widget.ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_vie_for_list, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            movie: Movie
        ) {
            itemView.movie_title.text = movie.title
            itemView.movie_year.text = movie.year.toString()
            itemView.movie_genre.text = movie.genre
            itemView.movie_director.text = movie.director

            Glide.with(itemView)
                .load(movie.poster)
                .placeholder(R.drawable.load_imege)
                .into(itemView.movie_image)
                .view
        }
    }

    class MovieDiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
