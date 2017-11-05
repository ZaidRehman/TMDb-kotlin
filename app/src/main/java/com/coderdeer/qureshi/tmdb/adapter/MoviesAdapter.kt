package com.coderdeer.qureshi.tmdb.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.view.View
import android.widget.LinearLayout
import com.coderdeer.qureshi.tmdb.R
import com.coderdeer.qureshi.tmdb.model.Movie


class MoviesAdapter(private val movies: List<Movie>, private val rowLayout: Int, private val context: Context) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var moviesLayout: LinearLayout
        internal var movieTitle: TextView
        internal var data: TextView
        internal var movieDescription: TextView
        internal var rating: TextView

        init {

            moviesLayout = v.findViewById(R.id.movies_layout)
            movieTitle = v.findViewById(R.id.title)
            data = v.findViewById(R.id.subtitle)
            movieDescription = v.findViewById(R.id.description)
            rating = v.findViewById(R.id.rating)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                           viewType: Int): MoviesAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        holder.data.text = movies[position].releaseDate
        holder.movieDescription.text = movies[position].overview
        holder.rating.text = movies[position].voteAverage.toString()
    }
}