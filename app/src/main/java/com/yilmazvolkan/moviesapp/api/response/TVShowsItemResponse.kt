package com.yilmazvolkan.moviesapp.api.response

import com.google.gson.annotations.SerializedName

class TVShowsItemResponse(
    @SerializedName("poster_path")
    val imageUrl: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?
)