package com.abdulhakeem.tasveer.data.repository.photos

import com.abdulhakeem.tasveer.FakeDataRepository
import com.abdulhakeem.tasveer.data.MultiMediaContentResolver
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class LocalPhotoDataSourceImplTest {

    private lateinit var SUT: LocalPhotoDataSourceImpl
    private val multiMediaContentResolver: MultiMediaContentResolver = mockk()

    @Before
    fun setup() {
        SUT = LocalPhotoDataSourceImpl(multiMediaContentResolver = multiMediaContentResolver)
    }

    @Test
    fun `fetchExternalStorageMediaContents should call MultiMediaContentResolver to fetch content`() {

        every { multiMediaContentResolver.fetchExternalStorageMediaContents() } returns FakeDataRepository.provideMediaMetaDataList()

        SUT.fetchExternalStorageMediaContents()

        verify(exactly = 1) {
            multiMediaContentResolver.fetchExternalStorageMediaContents()
        }
    }


    @Test
    fun `fetchExternalStorageMediaContents should return unmodified data`() {
        val mockData = FakeDataRepository.provideMediaMetaDataList()
        every { multiMediaContentResolver.fetchExternalStorageMediaContents() } returns mockData

        val actualData = SUT.fetchExternalStorageMediaContents()

        assert(mockData == actualData)
    }

}