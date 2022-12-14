package com.abdulhakeem.tasveer.data

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MultiMediaContentResolver @Inject constructor(@ApplicationContext private val context: Context) {

    private val data = ArrayList<MediaMetaData>()


    private suspend fun fetchMediaFromPhoneAndPopulateTheList() {
        if (data.isNotEmpty())
            return

        val imagesCursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.MediaColumns.DATE_ADDED
            ),
            null,
            null,
            null
        )

        val videosCursor = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DATA,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.MediaColumns.DATE_ADDED
            ),
            null,
            null,
            null
        )

        try {

            imagesCursor?.run {
                readCursor(this)
            }
            videosCursor?.run {
                readCursor(this)
            }

            data.sortBy { it.dataAdded }


        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            imagesCursor?.close()
            videosCursor?.close()
        }
    }

    private fun readCursor(cursor: Cursor) {
        with(cursor) {
            while (moveToNext()) {
                val name =
                    getString(getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                val folder =
                    getString(getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                val dataPath = getString(getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                val contentType =
                    getString(getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE))
                val dateAdded = getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED))

                val mediaMetaData =
                    MediaMetaData(
                        name = name,
                        folderName = folder,
                        path = dataPath,
                        contentType = contentType,
                        dataAdded = dateAdded
                    )
                data.add(mediaMetaData)

            }
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

    suspend fun fetchAlbumMedia(albumName: String): List<Media> {


        fetchMediaFromPhoneAndPopulateTheList()

        return data.filter {
            it.folderName == albumName
        }.map {
            Media(
                thumbnail = it.path,
                albumName = it.folderName,
                photoName = it.name,
                mediaType = if (it.contentType.contains("video")) MediaType.Video else MediaType.Image
            )
        }
    }

}