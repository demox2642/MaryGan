package com.example.marygan.di

import com.example.second.repository.SecondRepository
import com.example.second.usecase.GetRaitingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesGetRaitingsUseCase(secondRepository: SecondRepository): GetRaitingsUseCase = GetRaitingsUseCase(secondRepository)
}
