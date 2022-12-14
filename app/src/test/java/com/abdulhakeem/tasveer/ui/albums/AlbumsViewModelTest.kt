package com.abdulhakeem.tasveer.ui.albums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abdulhakeem.tasveer.FakeDataRepository
import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.domain.FetchAlbumUseCase
import com.fallingwords.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class AlbumsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var SUT: AlbumsViewModel
    private val fetchAlbumUseCase: FetchAlbumUseCase = mockk()

    @Before
    fun setup() {
        SUT = AlbumsViewModel(fetchAlbumUseCase)
    }

    @Test
    fun `fetchAlbums should update livedata`() {
        val data = FakeDataRepository.provideAlbumsList()
        coEvery { fetchAlbumUseCase() } returns data

        SUT.fetchAlbums()

        assert(SUT.albums.value is List<Album>)
        assert((SUT.albums.value?.size ?: 0) == data.size)

    }
}