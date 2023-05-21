package com.sample.simpsonsviewer.repository
import com.sample.simpsonsviewer.repository.pojo.Simpsons
import retrofit2.Response

interface SimpsonsHomeRepository {
    suspend fun getData(): Response<Simpsons>
}