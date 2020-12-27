package com.yilmazvolkan.moviesapp.repository

import com.yilmazvolkan.moviesapp.models.Resource
import com.yilmazvolkan.moviesapp.models.map
import com.yilmazvolkan.moviesapp.models.TvShowItem
import io.reactivex.Observable
import javax.inject.Inject

class MoviesFactory @Inject constructor(
    private val repository: MoviesRepository,
    private val mapper: MoviesMapper
) {

    fun fetchMovies(page: Int): Observable<Resource<List<TvShowItem>>> {
        return repository
            .fetchMovies(page)
            .map { resource ->
                resource.map { response ->
                    mapper.mapFrom(response)
                }
            }.startWith(Resource.Loading)
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}