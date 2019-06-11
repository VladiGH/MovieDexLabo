package com.avgh.moviedexlaboev.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.avgh.moviedexlaboev.R
import com.avgh.moviedexlaboev.entity.Movie
import com.avgh.moviedexlaboev.entity.MoviePreview
import com.avgh.moviedexlaboev.viewmodel.MovieViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.cardview_movie.view.*

class movieDetailActivity : AppCompatActivity() {
    private lateinit var viewModelMovie: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        viewModelMovie = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        val movie: MoviePreview? = intent.getBundleExtra("bundle").getParcelable("MOVIE")
        if(movie!=null) init(movie)
    }

    private fun init(movie: MoviePreview) {
        viewModelMovie.apiGetMoviesByTitle(movie.Title)
        viewModelMovie.getMovieFulled().observe(this, Observer { peliculon->
            Glide.with(this)
                    .load(peliculon.Poster)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(IV_caratula)
            tv_Title.text= peliculon.Title
            tv_movie_year.text= peliculon.Year
            tv_movie_duracion.text= peliculon.Runtime
            tv_genre.text= peliculon.Genre
            tv_director.text= peliculon.Director
            tv_actores.text=peliculon.Actors
            tv_plot.text= peliculon.Plot
        })
    }
}
