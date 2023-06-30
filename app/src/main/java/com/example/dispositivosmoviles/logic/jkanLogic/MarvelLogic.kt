package com.example.dispositivosmoviles.logic.jkanLogic

import android.util.Log
import com.example.dispositivosmoviles.data.connetions.ApiConnection
import com.example.dispositivosmoviles.data.endpoint.JikanEndpoint
import com.example.dispositivosmoviles.data.endpoint.MarvelEndPoint
import com.example.dispositivosmoviles.ui.activities.entity.jkanmarvel.LoginMarvel

class MarvelLogic {

    suspend fun getCharactersMarvel(name: String, limit: Int): List<LoginMarvel> {

        val response =
            ApiConnection.getService(
                ApiConnection.typeApi.Marvel,
                MarvelEndPoint::class.java
            ).getCharactersStarWith(name, limit)

        var itemList = arrayListOf<LoginMarvel>()

        if (response.isSuccessful) {
            response.body()!!.data.results.forEach {
                var commic: String = "No available"
                if (it.comics.items.isNotEmpty()) {
                    commic = it.comics.items[0].name
                }
                val m = LoginMarvel(
                    it.id,
                    it.name,
                    commic,
                    it.thumbnail.path + "." + it.thumbnail.extension
                )
                itemList.add(m)
            }
        } else {
            Log.d("UCE", response.message())
        }
        return itemList
    }
}