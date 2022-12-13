package com.abdulhakeem.tasveer.di

import com.abdulhakeem.tasveer.data.LocalPhotoDataSource
import com.abdulhakeem.tasveer.data.LocalPhotoDataSourceImpl
import com.abdulhakeem.tasveer.data.PhotoRepository
import com.abdulhakeem.tasveer.data.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun providePhotoRepository(repo: PhotoRepositoryImpl): PhotoRepository

    @Binds
    @ViewModelScoped
    fun providePhotosLocalDataSource(local: LocalPhotoDataSourceImpl): LocalPhotoDataSource

}