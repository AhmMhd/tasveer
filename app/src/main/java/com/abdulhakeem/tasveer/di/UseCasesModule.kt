package com.abdulhakeem.tasveer.di

import com.abdulhakeem.tasveer.domain.FetchAlbumUseCase
import com.abdulhakeem.tasveer.domain.FetchAlbumUseCaseImpl
import com.abdulhakeem.tasveer.domain.FetchMediaByAlbumNameUserCase
import com.abdulhakeem.tasveer.domain.FetchMediaByAlbumNameUserCaseImpl
import com.abdulhakeem.tasveer.domain.FetchMediaByMediaTypeUseCase
import com.abdulhakeem.tasveer.domain.FetchMediaByMediaTypeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun provideFetchAlbumUseCase(fetchAlbumUseCaseImpl: FetchAlbumUseCaseImpl): FetchAlbumUseCase

    @Binds
    fun provideFetchMediaByAlbumNameUseCase(fetchMediaByAlbumNameUserCaseImpl: FetchMediaByAlbumNameUserCaseImpl): FetchMediaByAlbumNameUserCase

    @Binds
    fun provideFetchMediaByMediaTypeMediaType(fetchMediaByMediaTypeUseCaseImpl: FetchMediaByMediaTypeUseCaseImpl): FetchMediaByMediaTypeUseCase

}