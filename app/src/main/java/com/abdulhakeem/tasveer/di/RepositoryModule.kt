package com.abdulhakeem.tasveer.di

import com.abdulhakeem.tasveer.data.repository.photos.LocalPhotoDataSource
import com.abdulhakeem.tasveer.data.repository.photos.LocalPhotoDataSourceImpl
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepository
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun providePhotoRepository(repo: PhotoRepositoryImpl): PhotoRepository

    @Binds
    fun providePhotosLocalDataSource(local: LocalPhotoDataSourceImpl): LocalPhotoDataSource

}