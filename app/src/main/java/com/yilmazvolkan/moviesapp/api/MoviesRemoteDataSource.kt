package com.yilmazvolkan.moviesapp.api

import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val moviesService: MoviesService) {

    fun fetchMovies(page: Int) = moviesService.getMovies(page)

}