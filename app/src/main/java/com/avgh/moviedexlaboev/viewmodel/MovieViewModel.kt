package com.avgh.moviedexlaboev.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avgh.moviedexlaboev.database.RoomDB
import com.avgh.moviedexlaboev.entity.Movie
import com.avgh.moviedexlaboev.entity.MoviePreview
import com.avgh.moviedexlaboev.network.Interceptor
import com.avgh.moviedexlaboev.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(val app: Application) : AndroidViewModel(app) {

    private val repository: MovieRepository
    private val movieResult = MutableLiveData<Movie>()
    private val movieslist = MutableLiveData<MutableList<MoviePreview>>()
    init{
        val movieDao = RoomDB.getDatabase(app).movieDao()
        repository = MovieRepository(movieDao, Interceptor.ombdApi)
    }
    fun insert(movie: Movie) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(movie)
    }

    fun getAll(): LiveData<List<Movie>> = repository.getAllfromRoomDB()

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAll()
        }
    }

    fun getMovieByName(name: String): LiveData<List<Movie>> = repository.getMovieByName(name)

    fun apiGetMoviesByTitle(name: String){
        CoroutineScope(Dispatchers.IO).launch{
            val response=repository.apiGetMoviesByTitle(name).await()
            if(response.isSuccessful) {
                if(response.code()==200){
                    movieResult.postValue(response.body())
                }
            }else{
                Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun getMovieFulled(): LiveData<Movie> = movieResult

    fun apiGetMoviesByName(name: String){
        CoroutineScope(Dispatchers.IO).launch {
            Log.v("api", "llego1")
            val response=repository.apiGetMoviesByName(name).await()
            if(response.isSuccessful){
                if(response.code()==200){
                    movieslist.postValue(response.body()?.Search?.toMutableList()?:arrayListOf(MoviePreview()))
                }
            }else{
                Log.v("api", "llego2")
                //Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getMovieListPreview(): LiveData<MutableList<MoviePreview>> = movieslist
}