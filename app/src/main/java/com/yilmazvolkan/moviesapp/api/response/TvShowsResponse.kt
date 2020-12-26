package com.yilmazvolkan.moviesapp.api.response

import com.google.gson.annotations.SerializedName

class TvShowsResponse(
    @SerializedName("results")
    val results: List<TVShowsItemResponse>
)