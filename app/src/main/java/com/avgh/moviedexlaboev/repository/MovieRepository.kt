package com.avgh.moviedexlaboev.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.avgh.moviedexlaboev.database.dao.MovieDao
import com.avgh.moviedexlaboev.database.entity.Movie

class MovieRepository(private val movieDao: MovieDao) {

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromRoomDB(): LiveData<List<Movie>> = movieDao.loadAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)

    //todo: faltaria lo de retrofit
}