package com.thiagoio.listmovies.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiagoio.listmovies.framework.api.MovieRestApiTask
import com.thiagoio.listmovies.data.MovieRepository
import com.thiagoio.listmovies.domain.Movie
import com.thiagoio.listmovies.implementation.MovieDataSourceImplementation
import com.thiagoio.listmovies.usecase.MoviesListUseCase

class MovieListViewModel : ViewModel() {

    companion object {
        const val TAG = "MovieRepository"
    }


    private val movieRestApiTask = MovieRestApiTask()
    private val movieDataSource =  MovieDataSourceImplementation(movieRestApiTask)
    private val movieRepository = MovieRepository(movieDataSource)
    private val movieListUseCase = MoviesListUseCase(movieRepository)


    private val _movieLiveData = MutableLiveData<List<Movie>>()
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData


    fun init() {
        getAllMovies()
    }

    private fun getAllMovies() {
        Thread{
            try {
                _movieLiveData.postValue(movieListUseCase.invoke())
            } catch (exception: Exception) {
                Log.d(TAG, exception.message.toString())
            }
        }.start()

    }
}