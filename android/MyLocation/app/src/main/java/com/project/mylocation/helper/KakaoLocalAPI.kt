package com.project.mylocation.helper

import com.project.mylocation.model.ResultGetLocation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoLocalAPI {
    @GET("v2/local/search/category.json")
    fun getLocalByCategory(
        @Header("Authorization") apiKey: String,
        @Query("category_group_code") category: String,
        @Query("x") x: Double,
        @Query("y") y: Double,
        @Query("radius") radius: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Call<ResultGetLocation>
}