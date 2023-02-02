package com.example.second.usecase

import com.example.second.models.UserRaitings
import com.example.second.repository.SecondRepository
import kotlinx.coroutines.flow.Flow

class GetRaitingsUseCase(private val secondRepository: SecondRepository) {
    suspend fun getRaitings(): Flow<List<UserRaitings>> = secondRepository.getRaitings()
}