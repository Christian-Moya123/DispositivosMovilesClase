package com.example.dispositivosmoviles.data.endpoint

import com.example.dispositivosmoviles.ui.activities.entity.jkan.JikanAnimeEntity
import retrofit2.Response
import retrofit2.http.GET

interface JikanEndpoint {
    @GET("top/anime")
    suspend fun getAllAnimes(): Response<JikanAnimeEntity>

}