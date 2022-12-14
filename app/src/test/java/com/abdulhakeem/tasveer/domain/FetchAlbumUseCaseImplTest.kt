package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.FakeDataRepository
import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchAlbumUseCaseImplTest {

    private lateinit var SUT: FetchAlbumUseCaseImpl
    private val photoRepository: PhotoRepository = mockk()

    @Before
    fun setup() {
        SUT = FetchAlbumUseCaseImpl(photoRepository)
    }

    @Test
    fun `invoking the usecase should add two extra folders then what is already there in the storage`() {
        val data = FakeDataRepository.provideMediaMetaDataList()
        val totalNumberOfFolders = data.distinctBy { it.folderName }.size

        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        runBlocking {
            val result = SUT()
            assert(totalNumberOfFolders.plus(2) == result.size)
        }
    }

    @Test
    fun `invoking the usecase should only return distinct folders`() {
        val data = FakeDataRepository.provideMediaMetaDataList()

        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        runBlocking {
            val result = SUT()
            val distinctFolder = result.distinctBy { it.albumName }
            assert(result.size == distinctFolder.size)
        }
    }

    @Test
    fun `invoking the usecase should return list of albums`() {
        val data = FakeDataRepository.provideMediaMetaDataList()

        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        runBlocking {
            val result = SUT()
            assert(result.first() is Album)
        }
    }

    @Test
    fun `invoking the usecase should map MediaMetaData To Album correctly`() {
        val data = FakeDataRepository.provideMediaMetaDataList()
        val distinctFolder = data.distinctBy { it.folderName }

        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        runBlocking {
            val result = SUT()
//            starting from index 2, because 0,1 index have new folders
            for (i in 2 until data.size) {
                assert(result[i].thumbnail == distinctFolder[i - 2].path)
            }
        }
    }

    @Test
    fun `invoking the usecase should return list of albums where index 0 and 1 are 'all images' and 'all videos' respectively`() {
        val data = FakeDataRepository.provideMediaMetaDataList()
        every { photoRepository.fetchExternalStorageMediaContents() } returns data
        runBlocking {
            val result = SUT()
            assert(result[0].albumName.equals("all images", true))
            assert(result[1].albumName.equals("all videos", true))
        }
    }
}