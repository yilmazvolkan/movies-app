package com.yilmazvolkan.moviesapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yilmazvolkan.moviesapp.R
import com.yilmazvolkan.moviesapp.databinding.ItemTvShowBinding
import com.yilmazvolkan.moviesapp.models.TvShowItem
import com.yilmazvolkan.moviesapp.ui.inflate
import com.yilmazvolkan.moviesapp.ui.viewStates.TVShowsItemViewState
import javax.inject.Inject

class TVShowsFeedAdapter @Inject constructor() :
    RecyclerView.Adapter<TVShowsFeedAdapter.PopularTVShowsFeedItemViewHolder>() {

    private var popularTvShows: MutableList<TvShowItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTVShowsFeedItemViewHolder {
        val itemBinding = parent.inflate<ItemTvShowBinding>(R.layout.item_tv_show, false)
        return PopularTVShowsFeedItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = popularTvShows.size

    override fun onBindViewHolder(holder: PopularTVShowsFeedItemViewHolder, position: Int) {
        holder.bind(getTvShow(position))
    }

    private fun getTvShow(position: Int) = popularTvShows[position]

    fun setTvShows(tvShows: List<TvShowItem>) {
        val beforeSize = popularTvShows.size
        popularTvShows.addAll(tvShows)
        notifyItemRangeInserted(beforeSize, tvShows.size)
    }

    inner class PopularTVShowsFeedItemViewHolder(private val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShowItem) {
            with(binding) {
                viewState = TVShowsItemViewState(tvShow)
                executePendingBindings()
            }
        }

    }
}