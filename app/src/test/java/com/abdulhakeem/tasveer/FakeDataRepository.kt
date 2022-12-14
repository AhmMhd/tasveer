package com.abdulhakeem.tasveer

import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.data.model.AlbumType
import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.data.model.MediaMetaData
import com.abdulhakeem.tasveer.data.model.MediaType

object FakeDataRepository {
    fun provideMediaMetaDataList() = ArrayList<MediaMetaData>().apply {
        add(MediaMetaData("dummy name", "dummay/path/here", "folder 1", "video/mp4", 0))
        add(MediaMetaData("dummy name", "dummay/path/here", "folder 2", "image/png", 0))
        add(MediaMetaData("dummy name", "dummay/path/here", "folder 3", "video/mp4", 0))
        add(MediaMetaData("dummy name", "dummay/path/here", "folder 4", "image/png", 0))
        add(MediaMetaData("dummy name", "dummay/path/here", "folder 5", "video/mp4", 0))
        add(MediaMetaData("dummy name", "dummay/path/here", "folder 6", "image/png", 0))
    }

    fun provideAlbumsList() = ArrayList<Album>().apply {
        add(Album("dummy/path/here", "dummay name", AlbumType.Video))
        add(Album("dummy/path/here", "dummay name", AlbumType.Video))
        add(Album("dummy/path/here", "dummay name", AlbumType.Video))
        add(Album("dummy/path/here", "dummay name", AlbumType.Video))
        add(Album("dummy/path/here", "dummay name", AlbumType.Video))
        add(Album("dummy/path/here", "dummay name", AlbumType.Video))
        add(Album("dummy/path/here", "dummay name", AlbumType.Video))
    }


    fun provideMediaList() = ArrayList<Media>().apply {
        add(Media("dummy name", "dummay/path/here", "name", MediaType.Image))
        add(Media("dummy name", "dummay/path/here", "name", MediaType.Image))
        add(Media("dummy name", "dummay/path/here", "name", MediaType.Image))
        add(Media("dummy name", "dummay/path/here", "name", MediaType.Image))
        add(Media("dummy name", "dummay/path/here", "name", MediaType.Image))
        add(Media("dummy name", "dummay/path/here", "name", MediaType.Image))
        add(Media("dummy name", "dummay/path/here", "name", MediaType.Image))
    }
}