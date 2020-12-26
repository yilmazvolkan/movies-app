package com.yilmazvolkan.moviesapp.repository

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}