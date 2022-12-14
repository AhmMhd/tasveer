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

        val albums = ArrayList<Album>()


        data.firstOrNull {
            it.contentType.contains("video").not()
        }?.let {
            albums.add(Album(thumbnail = it.path, albumName = "All Images", AlbumType.Image))
        }

        data.firstOrNull {
            it.contentType.contains("video")
        }?.let {
            albums.add(Album(thumbnail = it.path, albumName = "All Videos", AlbumType.Video))
        }

        data.distinctBy {
            it.folderName
        }.map {
            val albumType = when (it.folderName.lowercase()) {
                "all images" -> AlbumType.Image
                "all videos" -> AlbumType.Image
                else -> AlbumType.Mix
            }
            Album(thumbnail = it.path, albumName = it.folderName, albumType)
        }.let {
            albums.addAll(it)
        }

        return albums
    }

    suspend fun fetchMediaByAlbumName(albumName: String): List<Media> {


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

    suspend fun fetchMediaByMediaType(albumType: AlbumType): List<Media> {

        fetchMediaFromPhoneAndPopulateTheList()

        return data.filter {
            when (albumType) {
                AlbumType.Video -> it.contentType.contains("video")
                else -> it.contentType.contains("video").not()
            }
        }.map {
            Media(
                it.path,
                it.folderName,
                it.name,
                mediaType = if (it.contentType.contains("video")) MediaType.Video else MediaType.Image
            )
        }
    }
}