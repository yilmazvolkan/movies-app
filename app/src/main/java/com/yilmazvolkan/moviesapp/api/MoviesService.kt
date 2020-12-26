package com.yilmazvolkan.moviesapp.api

import io.reactivex.Observable
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("tv/popular")
    fun fetchMovies(@Query("page") page: Int): Observable<Response>
}