package com.sample.character.repository
import com.sample.character.repository.pojo.Simpsons
import retrofit2.Response

interface SimpsonsHomeRepository {
    suspend fun getData(): Response<Simpsons>
}