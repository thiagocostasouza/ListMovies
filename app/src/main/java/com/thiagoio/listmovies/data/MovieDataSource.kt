package com.thiagoio.listmovies.data

import com.thiagoio.listmovies.domain.Movie

interface MovieDataSource {

    fun getAllMovies(): List<Movie>
}