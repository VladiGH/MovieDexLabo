package com.avgh.moviedexlaboev.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avgh.moviedexlaboev.entity.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("select * from Movie")
    fun loadAllMovies(): LiveData<List<Movie>>

    //TODO: modificar para que la querie sera por coincidencia
    @Query("select * from Movie where Title like :name")
    fun searchMovieByName(name: String): LiveData<List<Movie>>

    @Query("DELETE from Movie")
    suspend fun deleteAll()
}