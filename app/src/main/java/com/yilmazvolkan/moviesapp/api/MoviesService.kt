package com.yilmazvolkan.moviesapp.api

import com.yilmazvolkan.moviesapp.api.response.TvShowsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("tv/popular")
    fun getMovies(@Query("page") page: Int): Observable<TvShowsResponse>
}