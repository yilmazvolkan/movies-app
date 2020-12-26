package com.yilmazvolkan.moviesapp.repository

import com.yilmazvolkan.moviesapp.api.response.TvShowsResponse
import com.yilmazvolkan.moviesapp.models.TvShowItem
import javax.inject.Inject

class MoviesMapper @Inject constructor() : Mapper<TvShowsResponse, List<TvShowItem>> {

    override fun mapFrom(type: TvShowsResponse): List<TvShowItem> {
        return type.results.map { itemResponse ->
            TvShowItem(
                imageUrl = itemResponse.imageUrl,
                firstAirDate = itemResponse.firstAirDate,
                name = itemResponse.name,
                overview = itemResponse.overview,
                voteAverage = itemResponse.voteAverage
            )
        }
    }
}