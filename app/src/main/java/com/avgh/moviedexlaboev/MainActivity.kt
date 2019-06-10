package com.avgh.moviedexlaboev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.avgh.moviedexlaboev.Adapters.movieAdapter
import com.avgh.moviedexlaboev.entity.Movie
import com.avgh.moviedexlaboev.entity.MoviePreview
import com.avgh.moviedexlaboev.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var moviesAdapter: movieAdapter
    private lateinit var viewModelMovie: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelMovie = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        moviesAdapter= movieAdapter(ArrayList<MoviePreview>(), { movie: MoviePreview ->nuevaActivityPelicula(movie)})
        rv_list.apply {
            adapter= moviesAdapter
            layoutManager =  StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        }

        viewModelMovie.getAll().observe(this, Observer {
            moviesAdapter.changeDataSetFulled(it)
        })

        bt_search.setOnClickListener {
            val movieNameQuery = et_search.text.toString()
            if (movieNameQuery.isNotEmpty() && movieNameQuery.isNotBlank()) {
                viewModelMovie.apiGetMoviesByName(movieNameQuery)
                viewModelMovie.getMovieListPreview().observe(this, Observer { lista ->
                    moviesAdapter.changeDataSet(lista)
                })
            }
        }
        bt_guardarEnRoom.setOnClickListener(){
            viewModelMovie.deleteAll()
            for(movie in viewModelMovie.getMovieListPreview().value?: emptyList<MoviePreview>()){
                viewModelMovie.apiGetMoviesByTitle(movie.Title)
                viewModelMovie.getMovieFulled().observe(this, Observer {
                    viewModelMovie.insert(it)
                    Log.v("insert", it.Title)
                })
            }
        }
    }
    private fun nuevaActivityPelicula(movie: MoviePreview) {
        viewModelMovie.apiGetMoviesByTitle(movie.Title)
        viewModelMovie.getMovieFulled().observe(this, Observer {movie->
            Log.v("movieCompleta", movie.Title)
            Log.v("movieCompleta", movie.Year)
            Log.v("movieCompleta", movie.Released)
            Log.v("movieCompleta", movie.Runtime)
            Log.v("movieCompleta", movie.Genre)
            Log.v("movieCompleta", movie.Actors)

        })
    }
}
