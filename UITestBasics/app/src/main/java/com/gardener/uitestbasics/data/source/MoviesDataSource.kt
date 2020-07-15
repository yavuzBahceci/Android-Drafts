package com.gardener.uitestbasics.data.source

import com.gardener.uitestbasics.data.Movie


interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}