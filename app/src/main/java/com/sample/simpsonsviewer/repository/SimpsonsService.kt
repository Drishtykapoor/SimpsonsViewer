package com.sample.simpsonsviewer.repository


import com.sample.simpsonsviewer.repository.pojo.Simpsons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SimpsonsService {

    @Headers("Content-Type: application/json")
    @GET("/")
    suspend fun getData(
        @Query("q") q: String = "simpsons characters",
        @Query("format") format: String = "json"
    ): Response<Simpsons>

}