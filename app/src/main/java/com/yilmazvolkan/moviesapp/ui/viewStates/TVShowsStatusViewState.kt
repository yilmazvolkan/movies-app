package com.yilmazvolkan.moviesapp.ui.viewStates

import com.yilmazvolkan.moviesapp.models.Status

class TVShowsStatusViewState(
    private val status: Status
) {
    fun isLoading() = status is Status.Loading

    fun getErrorMessage() = if (status is Status.Error) status.exception.message else ""

    fun shouldShowErrorMessage() = status is Status.Error
}