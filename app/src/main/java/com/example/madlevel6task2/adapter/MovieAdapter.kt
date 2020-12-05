package com.example.madlevel6task2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task2.Model.Movie
import com.example.madlevel6task2.R

class MovieAdapter(private val movies: List<Movie>, private val onClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {

        }

        fun databind(movie: Movie){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_overview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.databind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}