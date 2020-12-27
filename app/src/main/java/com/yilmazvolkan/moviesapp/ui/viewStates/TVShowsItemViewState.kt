package com.yilmazvolkan.moviesapp.ui.viewStates

import com.yilmazvolkan.moviesapp.models.TvShowItem

class TVShowsItemViewState(private val tvShow: TvShowItem) {

    fun getImageUrl() = tvShow.imageUrl

    fun getTvShowName() = tvShow.name

    fun getTvShowOverview() = tvShow.overview

    fun getTvAirDate() = tvShow.firstAirDate

    fun getVoteAverage() = tvShow.voteAverage.toString()
}