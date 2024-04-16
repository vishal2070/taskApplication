package com.example.taskapplication.db

import com.example.taskapplication.DataClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getData(): List<DataClass>

}