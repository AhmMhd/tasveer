package com.abdulhakeem.tasveer.ui.media

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abdulhakeem.tasveer.FakeDataRepository
import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.data.model.AlbumType
import com.abdulhakeem.tasveer.domain.FetchMediaByAlbumNameUserCase
import com.abdulhakeem.tasveer.domain.FetchMediaByMediaTypeUseCase
import com.fallingwords.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PhotosViewModelTest {

    private lateinit var SUT: PhotosViewModel
    private val fetchMediaByMediaTypeUseCase: FetchMediaByMediaTypeUseCase = mockk()
    private val fetchMediaByAlbumNameUserCase: FetchMediaByAlbumNameUserCase = mockk()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        SUT = PhotosViewModel(
            fetchMediaByMediaTypeUseCase = fetchMediaByMediaTypeUseCase,
            fetchMediaByAlbumNameUserCase = fetchMediaByAlbumNameUserCase
        )
    }

    @Test
    fun `handleArgs should update the albumName`() {
        coEvery { fetchMediaByAlbumNameUserCase(any()) } returns FakeDataRepository.provideMediaList()
        coEvery { fetchMediaByMediaTypeUseCase(any()) } returns FakeDataRepository.provideMediaList()

        val args = PhotosFragmentArgs(Album("path/path", "album name"))
        SUT.handleArgs(args)

        assert(SUT.albumName == args.album.albumName)
    }


    @Test
    fun `handleArgs with AlbumType_Mix should call fetchMediaByAlbumNameUserCase`() {
        coEvery { fetchMediaByAlbumNameUserCase(any()) } returns FakeDataRepository.provideMediaList()
        coEvery { fetchMediaByMediaTypeUseCase(any()) } returns FakeDataRepository.provideMediaList()

        val args = PhotosFragmentArgs(Album("path/path", "album name"))
        SUT.handleArgs(args)

        coVerify(exactly = 1) {
            fetchMediaByAlbumNameUserCase(any())
        }

        coVerify(exactly = 0) {
            fetchMediaByMediaTypeUseCase(any())
        }
    }

    @Test
    fun `handleArgs with AlbumType_VIDEO should call fetchMediaByAlbumNameUserCase`() {
        coEvery { fetchMediaByAlbumNameUserCase(any()) } returns FakeDataRepository.provideMediaList()
        coEvery { fetchMediaByMediaTypeUseCase(any()) } returns FakeDataRepository.provideMediaList()

        val args = PhotosFragmentArgs(Album("path/path", "album name", AlbumType.Video))
        SUT.handleArgs(args)

        coVerify(exactly = 0) {
            fetchMediaByAlbumNameUserCase(any())
        }

        coVerify(exactly = 1) {
            fetchMediaByMediaTypeUseCase(any())
        }
    }
}