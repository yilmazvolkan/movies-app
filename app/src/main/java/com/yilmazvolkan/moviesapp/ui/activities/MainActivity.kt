package com.yilmazvolkan.moviesapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.yilmazvolkan.moviesapp.R
import com.yilmazvolkan.moviesapp.databinding.ActivityMainBinding
import com.yilmazvolkan.moviesapp.ui.observeNonNull
import com.yilmazvolkan.moviesapp.ui.viewStates.TVShowsStatusViewState
import com.yilmazvolkan.moviesapp.viewModels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val moviesViewModel: MoviesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        observeMoviesViewModel()
    }

    private fun observeMoviesViewModel() = with(moviesViewModel) {
        contents_.observeNonNull(this@MainActivity) { contents ->
            /** */
        }
        status_.observeNonNull(this@MainActivity) { status ->
            renderStatusResult(status)
        }
    }

    private fun renderStatusResult(statusViewState: TVShowsStatusViewState) {
        binding.viewState = statusViewState
        binding.executePendingBindings()
    }
}