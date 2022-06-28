package gam.trainingcourse.annht2_advanceandroid_day6_component2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gam.trainingcourse.annht2_advanceandroid_day6_component2.databinding.ItemFilmBinding
import gam.trainingcourse.annht2_advanceandroid_day6_component2.model.Movie

class MovieAdapter(val context: Context, private val listMovies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movie = listMovies[position]
    }

    override fun getItemCount(): Int = listMovies.size


}