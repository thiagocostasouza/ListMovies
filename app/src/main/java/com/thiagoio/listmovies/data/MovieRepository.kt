package com.thiagoio.listmovies.data

class MovieRepository(private val movieDataSource: MovieDataSource) {

    fun getAllMoviesFromDataSource() = movieDataSource.getAllMovies()

}