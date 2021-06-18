package com.thiagoio.listmovies.presenter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.thiagoio.listmovies.R
import com.thiagoio.listmovies.domain.Movie
import com.thiagoio.listmovies.framework.viewmodel.MovieListViewModel

class MainActivity : AppCompatActivity() {
    private val movieList by lazy { findViewById<RecyclerView>(R.id.moviesList) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieListViewModel =
            ViewModelProvider.NewInstanceFactory().create(MovieListViewModel::class.java)
        movieListViewModel.init()
        initObserver()
        loadingVisibility(true)
    }

    private fun initObserver() {
        movieListViewModel.movieLiveData.observe(this, { list ->
            if (list.isNotEmpty()) {
                loadingVisibility(false)
                populateList(list)

            }
        })
    }

    private fun populateList(list: List<Movie>) {
        movieList.apply {
            hasFixedSize()
            adapter = MoviesAdapter(list)
        }
    }

    private fun loadingVisibility(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}