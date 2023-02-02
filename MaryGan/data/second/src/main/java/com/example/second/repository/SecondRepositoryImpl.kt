package com.example.second.repository

import com.example.second.models.UserRaitings
import com.example.second.models.toList
import com.example.second.services.SecondServises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SecondRepositoryImpl @Inject constructor(
    private val secondService: SecondServises): SecondRepository {
    override suspend fun getRaitings(): Flow<List<UserRaitings>> {
        val response = secondService.getRaitings()
        return if (response.isSuccessful){
            flow { emit(response.body()!!.raitings.toList()) }.flowOn(Dispatchers.IO)
        }else{
            flow { emptyList<UserRaitings>() }
        }
    }
}