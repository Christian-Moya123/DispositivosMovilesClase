package com.example.dispositivosmoviles.data.connetions

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnection {

    fun getJcanConnection(): Retrofit {
        var retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}