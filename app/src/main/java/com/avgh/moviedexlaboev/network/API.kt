package com.avgh.moviedexlaboev.network

import com.avgh.moviedexlaboev.entity.Movie
import com.avgh.moviedexlaboev.entity.OmbdMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("/")
    fun getMoviesByName(@Query("s") query: String): Deferred<Response<OmbdMovieResponse>>

    @GET("/")
    fun getMovieByTitle(@Query("t") query: String): Deferred<Response<Movie>>
}