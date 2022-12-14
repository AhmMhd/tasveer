package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.FakeDataRepository
import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchMediaByAlbumNameUserCaseImplTest {

    private lateinit var SUT: FetchMediaByAlbumNameUserCaseImpl
    private val photoRepository: PhotoRepository = mockk()

    @Before
    fun setup() {
        SUT = FetchMediaByAlbumNameUserCaseImpl(photoRepository)
    }


    @Test
    fun `invoking this usecase should return list of same folder`() {
        val data = FakeDataRepository.provideMediaMetaDataList()

        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        val folderName = "folder 1"
        runBlocking {
            val result = SUT(folderName)
            result.forEach {
                assert(it.albumName == folderName)
            }
        }
    }

    @Test
    fun `invoking this usecase with wrong album name should return empty list`() {
        val data = FakeDataRepository.provideMediaMetaDataList()

        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        val folderName = "folder x"
        runBlocking {
            val result = SUT(folderName)
            assert(result.isEmpty())
        }
    }

    @Test
    fun `invoking this usecase should return list of Media`() {
        val data = FakeDataRepository.provideMediaMetaDataList()

        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        val folderName = "folder 1"
        runBlocking {
            val result = SUT(folderName)
            assert(result.first() is Media)
        }
    }
}