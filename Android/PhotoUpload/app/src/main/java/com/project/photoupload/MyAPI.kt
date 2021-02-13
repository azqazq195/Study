package com.project.photoupload

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MyAPI {

    @Multipart
    @POST("/foret/photo")
    fun uploadImage(
        @Part files: List<MultipartBody.Part>,
        @Part("foret") foret: RequestBody
    ): Call<UploadResponse>

    companion object{
        private val BASE_URL = "http://192.168.55.172:8080"

        operator fun invoke() : MyAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyAPI::class.java)
        }
    }
}