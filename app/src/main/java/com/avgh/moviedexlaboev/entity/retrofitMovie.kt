package com.avgh.moviedexlaboev.entity

import android.os.Parcel
import android.os.Parcelable

data class MoviePreview(
    val Title: String = "N/A",
    val Year: String = "N/A",
    val imdbID: String = "N/A",
    val Type: String = "N/A",
    val Poster: String = "https://i.pinimg.com/originals/84/8e/cf/848ecf63259380f124d3e3ae11b04a43.jpg"
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeString(Year)
        parcel.writeString(imdbID)
        parcel.writeString(Type)
        parcel.writeString(Poster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviePreview> {
        override fun createFromParcel(parcel: Parcel): MoviePreview {
            return MoviePreview(parcel)
        }

        override fun newArray(size: Int): Array<MoviePreview?> {
            return arrayOfNulls(size)
        }
    }
}

data class OmbdMovieResponse (
    val Search: List<MoviePreview>,
    val totalResults: String,
    val Response: String
)