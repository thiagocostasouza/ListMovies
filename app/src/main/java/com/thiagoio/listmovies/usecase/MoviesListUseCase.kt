package com.thiagoio.listmovies.usecase

import com.thiagoio.listmovies.data.MovieRepository

class MoviesListUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke() = movieRepository.getAllMoviesFromDataSource()
}