package com.abdulhakeem.tasveer.data.repository.photos

import com.abdulhakeem.tasveer.FakeDataRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PhotoRepositoryImplTest {

    private lateinit var SUT: PhotoRepositoryImpl
    private val localSource: LocalPhotoDataSource = mockk()

    @Before
    fun setup() {
        SUT = PhotoRepositoryImpl(localSource)
    }

    @Test
    fun `fetchExternalStorageMediaContents should call LocalPhotoDataSource to fetch content`() {

        every { localSource.fetchExternalStorageMediaContents() } returns FakeDataRepository.provideMediaMetaDataList()

        SUT.fetchExternalStorageMediaContents()

        verify(exactly = 1) {
            localSource.fetchExternalStorageMediaContents()
        }
    }


    @Test
    fun `fetchExternalStorageMediaContents should return unmodified data as return from local source`() {
        val mockData = FakeDataRepository.provideMediaMetaDataList()
        every { localSource.fetchExternalStorageMediaContents() } returns mockData

        val actualData = SUT.fetchExternalStorageMediaContents()

        assert(mockData == actualData)
    }
}