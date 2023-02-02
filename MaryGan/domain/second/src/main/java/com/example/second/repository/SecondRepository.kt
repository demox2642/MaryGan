package com.example.second.repository

import com.example.second.models.UserRaitings
import kotlinx.coroutines.flow.Flow

interface SecondRepository {
    suspend fun getRaitings(): Flow<List<UserRaitings>>
}
