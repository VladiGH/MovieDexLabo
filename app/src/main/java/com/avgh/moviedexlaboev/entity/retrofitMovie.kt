package com.avgh.moviedexlaboev.entity

data class MoviePreview(
    val Title: String = "N/A",
    val Year: String = "N/A",
    val imdbID: String = "N/A",
    val Type: String = "N/A",
    val Poster: String = "https://i.pinimg.com/originals/84/8e/cf/848ecf63259380f124d3e3ae11b04a43.jpg",
    var selected: Boolean = false
)

data class OmbdMovieResponse (
    val Search: List<MoviePreview>,
    val totalResults: String,
    val Response: String
)