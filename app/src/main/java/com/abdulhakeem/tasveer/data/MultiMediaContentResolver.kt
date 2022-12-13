package com.abdulhakeem.tasveer.data

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MultiMediaContentResolver @Inject constructor(@ApplicationContext private val context: Context) {

    private val data = ArrayList<MediaMetaData>()


    private fun fetchMediaFromPhoneAndPopulateTheList() {
        if (data.isNotEmpty())
            return

        val mediaStoreCursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.MediaColumns.MIME_TYPE
            ),
            null,
            null,
            null
        )
        try {
            mediaStoreCursor?.run {
                moveToNext()
                do {
                    val name =
                        getString(getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                    val folder =
                        getString(getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                    val dataPath = getString(getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    val contentType =
                        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE))
                    val mediaMetaData =
                        MediaMetaData(
                            name = name,
                            folderName = folder,
                            path = dataPath,
                            contentType = contentType
                        )
                    data.add(mediaMetaData)
                    Log.d("TasveerPhoto", mediaMetaData.toString())
                } while (moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mediaStoreCursor?.close()
        }
    }

    suspend fun fetchAlbums(): List<Album> {

        fetchMediaFromPhoneAndPopulateTheList()

        return data.distinctBy {
            it.folderName
        }.map {
            Album(thumbnail = it.path, albumName = it.folderName)
        }
    }

    suspend fun fetchAlbumMedia(albumPath: String): List<Photo> {

        fetchMediaFromPhoneAndPopulateTheList()

        return data.filter {
            it.path == albumPath
        }.map {
            Photo(thumbnail = it.folderName, albumName = it.folderName, photoName = it.name)
        }
    }


}