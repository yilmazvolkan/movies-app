package com.yilmazvolkan.moviesapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.yilmazvolkan.moviesapp.models.Resource
import com.yilmazvolkan.moviesapp.models.TvShowItem
import com.yilmazvolkan.moviesapp.repository.MoviesFactory
import com.yilmazvolkan.moviesapp.ui.viewStates.TVShowsStatusViewState
import com.yilmazvolkan.moviesapp.viewModels.MoviesViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PopularTVShowsViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    lateinit var moviesFactory: MoviesFactory

    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun before() {
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(moviesFactory)
    }

    @Test
    fun `that until first page is being loaded, then status should be loading`() {
        // Given
        val mockedObserver = createTVShowsFeedObserver()
        moviesViewModel.getStatus.observeForever(mockedObserver)

        every { moviesFactory.fetchMovies(any()) } returns
                Observable.just(Resource.Loading)

        // When
        moviesViewModel.fetchMovies(1)

        // Then
        val slot = slot<TVShowsStatusViewState>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.isLoading()).isTrue()
    }

    @Test
    fun `that first page is loaded, then status should be success and list size should be more than zero`() {
        // Given
        val mockedObserver = createTVShowsFeedObserver()
        moviesViewModel.getStatus.observeForever(mockedObserver)
        val mockedDataObserver = createTVShowsContentObserver()
        moviesViewModel.getContents.observeForever(mockedDataObserver)
        val listSize = 5

        every { moviesFactory.fetchMovies(any()) } returns
                Observable.just(Resource.Success(createDummyTvShowItemList(listSize)))

        // When
        moviesViewModel.fetchMovies(1)

        // Then
        val slot = slot<TVShowsStatusViewState>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.isSuccess()).isTrue()

        val slotSize = slot<List<TvShowItem>>()
        verify { mockedDataObserver.onChanged(capture(slotSize)) }

        assertThat(slotSize.captured.size).isEqualTo(listSize)
    }

    private fun createDummyTvShowItemList(size: Int): List<TvShowItem> {
        val result = mutableListOf<TvShowItem>()
        for (i in 1..size) {
            result.add(createDummyTvShow())
        }
        return result
    }

    private fun createDummyTvShow(): TvShowItem {
        return TvShowItem(
            imageUrl = "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
            firstAirDate = "2018-05-02",
            name = "Cobra Kai",
            overview = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            voteAverage = 8.1f
        )
    }

    private fun createTVShowsFeedObserver(): Observer<TVShowsStatusViewState> =
        spyk(Observer { })

    private fun createTVShowsContentObserver(): Observer<List<TvShowItem>> =
        spyk(Observer { })
}