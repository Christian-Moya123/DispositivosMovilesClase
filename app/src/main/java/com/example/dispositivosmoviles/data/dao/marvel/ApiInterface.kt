package com.example.dispositivosmoviles.data.dao.marvel

import com.mkrdeveloper.memeapp.models.AllMemsData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("get_memes")
    suspend fun getMemes() : Response<AllMemsData>
}