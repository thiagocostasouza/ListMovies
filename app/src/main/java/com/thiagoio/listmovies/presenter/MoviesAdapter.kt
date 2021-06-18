package com.thiagoio.listmovies.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.thiagoio.listmovies.R
import com.thiagoio.listmovies.domain.Movie


class MoviesAdapter(private val moviesList: List<Movie>) :
    RecyclerView.Adapter<MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemView.apply {
            val movieTitle = findViewById<AppCompatTextView>(R.id.movieTitle)
            val movieImage = findViewById<AppCompatImageView>(R.id.movieImage)

            movieTitle.text = moviesList[position].titulo
            movieImage.load(moviesList[position].imagem){
                placeholder(R.drawable.ic_android_black_24dp)
                fallback(R.drawable.ic_android_black_24dp)
            }
        }
    }

    override fun getItemCount(): Int = moviesList.size
}
