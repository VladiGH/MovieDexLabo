package com.avgh.moviedexlaboev.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avgh.moviedexlaboev.R
import com.avgh.moviedexlaboev.entity.Movie
import com.avgh.moviedexlaboev.entity.MoviePreview
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cardview_movie.view.*


class movieAdapter(var movies: List<MoviePreview>, val clickListener: (MoviePreview) -> Unit) : RecyclerView.Adapter<movieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position], clickListener)

    fun changeDataSet(newMovieList: List<MoviePreview>) {
        movies = newMovieList
        notifyDataSetChanged()
    }
    fun changeDataSetFulled(newMovieList: List<Movie>) {
        movies = emptyList()
        var list = mutableListOf<MoviePreview>()
        for(movie in newMovieList){
            list.add(MoviePreview(movie.Title, movie.Year, movie.imdbID, "", movie.Poster))
        }
        movies= list
        notifyDataSetChanged()
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        fun bind(movie: MoviePreview, clickListener: (MoviePreview) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(movie.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(movie_image_cv)
            movie_title_cv.text = movie.Title
            movie_imdbID.text= movie.imdbID
            movie_type.text= movie.Type
            movie_year.text= movie.Year
            this.setOnClickListener { clickListener(movie) }
        }
    }
}