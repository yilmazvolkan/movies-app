package com.yilmazvolkan.moviesapp.repository

import com.yilmazvolkan.moviesapp.api.MoviesRemoteDataSource
import com.yilmazvolkan.moviesapp.api.response.TvShowsResponse
import com.yilmazvolkan.moviesapp.models.Resource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies(page: Int): Observable<Resource<TvShowsResponse>> {
        return remoteDataSource
            .fetchMovies(page)
            .map<Resource<TvShowsResponse>> {
                Resource.Success(it)
            }.onErrorReturn { throwable ->
                Resource.Error(throwable)

            }.subscribeOn(Schedulers.io())

    }
}