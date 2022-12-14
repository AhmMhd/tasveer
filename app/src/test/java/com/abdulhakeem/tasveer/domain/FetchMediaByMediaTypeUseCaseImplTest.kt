package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.FakeDataRepository
import com.abdulhakeem.tasveer.data.model.AlbumType
import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.data.model.MediaType
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchMediaByMediaTypeUseCaseImplTest {
    private lateinit var SUT: FetchMediaByMediaTypeUseCaseImpl
    private val photoRepository: PhotoRepository = mockk()

    @Before
    fun setup() {
        SUT = FetchMediaByMediaTypeUseCaseImpl(photoRepository)
    }

    @Test
    fun `invoking this function should return list of media`() {
        val data = FakeDataRepository.provideMediaMetaDataList()
        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        runBlocking {
            val result = SUT(AlbumType.Image)
            assert(result.first() is Media)
        }
    }


    @Test
    fun `invoking this function should return list same media type`() {
        val data = FakeDataRepository.provideMediaMetaDataList()
        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        runBlocking {
            val imageResult = SUT(AlbumType.Image)
            imageResult.forEach {
                assert(it.mediaType == MediaType.Image)
            }

            val videoResult = SUT(AlbumType.Video)
            videoResult.forEach {
                assert(it.mediaType == MediaType.Video)
            }
        }
    }
}