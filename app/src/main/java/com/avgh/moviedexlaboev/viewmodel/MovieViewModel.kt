package com.avgh.moviedexlaboev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.avgh.moviedexlaboev.database.RoomDB
import com.avgh.moviedexlaboev.database.entity.Movie
import com.avgh.moviedexlaboev.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(val app: Application) : AndroidViewModel(app) {

    private val repository: MovieRepository

    init{
        val movieDao = RoomDB.getDatabase(app).movieDao()
        repository = MovieRepository(movieDao)
    }
    fun insert(movie: Movie) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(movie)
    }

    fun getAll(): LiveData<List<Movie>> = repository.getAllfromRoomDB()

    fun getMovieByName(name: String): LiveData<List<Movie>> = repository.getMovieByName(name)

}