package com.example.mytestapplication.network

import com.example.mytestapplication.data.App
import com.example.mytestapplication.data.RequestBody
import com.example.mytestapplication.data.ResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("list")
    suspend fun getAppList(@Query("kid_id")id :Int):Response<ResponseData>
}