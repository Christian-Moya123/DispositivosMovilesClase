package com.example.dispositivosmoviles.logic.jkanLogic

import com.example.dispositivosmoviles.data.connetions.ApiConnection
import com.example.dispositivosmoviles.data.endpoint.JikanEndpoint
import com.example.dispositivosmoviles.ui.activities.entity.jkanmarvel.LoginMarvel


class JikanLogic {

    suspend fun getAllAnimes():List<LoginMarvel>{
        /*
        var call = ApiConnection.getJcanConnection()
        val response = call.create(JikanEndpoint::class.java).getAllAnimes()


        var itemList = arrayListOf<LoginMarvel>()

        val response = ApiConnection.getService(
            ApiConnection.typeApi.Jikan,
            JikanEndpoint::class.java
        ).getAllAnimes()



        if(response.isSuccessful){
            response.body()!!.data.forEach{
                val m = LoginMarvel(it.mal_id,
                    it.title,
                    it.titles[0].title,
                    it.images.jpg.image_url)
                itemList.add(m)
            }
        }

        return itemList

         */

        var itemList = arrayListOf<LoginMarvel>()

        val response = ApiConnection.getService(
            ApiConnection.typeApi.Jikan,
            JikanEndpoint::class.java
        ).getAllAnimes()

        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                val m = LoginMarvel(
                    it.mal_id,
                    it.title,
                    it.titles[0].title,
                    it.images.jpg.image_url
                )
                itemList.add(m)
            }
        }
        return itemList
    }

}


