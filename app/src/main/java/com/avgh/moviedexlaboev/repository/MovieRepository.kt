package com.avgh.moviedexlaboev.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.avgh.moviedexlaboev.database.dao.MovieDao
import com.avgh.moviedexlaboev.entity.Movie
import com.avgh.moviedexlaboev.entity.OmbdMovieResponse
import com.avgh.moviedexlaboev.network.API
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val movieDao: MovieDao, private val api:API) {

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromRoomDB(): LiveData<List<Movie>> = movieDao.loadAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)

    fun apiGetMoviesByTitle(name: String): Deferred<Response<Movie>> {
        return api.getMovieByTitle(name);
    }
    fun apiGetMoviesByName(name: String): Deferred<Response<OmbdMovieResponse>> {
        return api.getMoviesByName(name);
    }
    @WorkerThread
    suspend fun deleteAll(){
        movieDao.deleteAll()
    }

    //todo: faltaria lo de retrofit
}