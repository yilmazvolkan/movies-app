package com.yilmazvolkan.moviesapp.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yilmazvolkan.moviesapp.models.Resource
import com.yilmazvolkan.moviesapp.models.Status
import com.yilmazvolkan.moviesapp.models.TvShowItem
import com.yilmazvolkan.moviesapp.repository.MoviesFactory
import com.yilmazvolkan.moviesapp.ui.viewStates.TVShowsStatusViewState
import io.reactivex.android.schedulers.AndroidSchedulers

class MoviesViewModel @ViewModelInject constructor(
    private val moviesFactory: MoviesFactory
) :
    ReactiveViewModel() {

    private val contents = MutableLiveData<List<TvShowItem>>()
    val contents_: LiveData<List<TvShowItem>> = contents

    private val status = MutableLiveData<TVShowsStatusViewState>()
    val status_: LiveData<TVShowsStatusViewState> = status

    fun fetchMovies(page: Int) {
        moviesFactory
            .fetchMovies(page)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                onMoviesContentResultReady(list)
            }
            .subscribe({ resource ->
                onMoviesStatusResultReady(resource)
            }, {})
            .also { disposable += it }
    }

    private fun onMoviesStatusResultReady(resource: Resource<List<TvShowItem>>) {

        val viewState = when (resource) {
            is Resource.Success -> TVShowsStatusViewState(Status.Content)
            is Resource.Error -> TVShowsStatusViewState(Status.Error(resource.exception))
            Resource.Loading -> TVShowsStatusViewState(Status.Loading)
        }
        status.value = viewState
    }

    private fun onMoviesContentResultReady(results: List<TvShowItem>) {
        contents.value = results
    }
}