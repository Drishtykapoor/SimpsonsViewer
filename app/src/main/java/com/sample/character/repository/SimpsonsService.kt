package com.sample.character.repository


import com.sample.character.MyData
import com.sample.character.repository.pojo.Simpsons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SimpsonsService {

    @Headers("Content-Type: application/json")
    @GET("/")
    suspend fun getData(
        @Query(value = "q", encoded = true) q: String = MyData.dataToFetch,
        @Query("format") format: String = "json"
    ): Response<Simpsons>

}