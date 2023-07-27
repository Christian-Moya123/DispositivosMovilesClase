package com.example.dispositivosmoviles.ui.utilities


import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.SettingsClient
import android.content.Context

class MyLocationManager (val context : Context) {
    private lateinit var client: SettingsClient

    private fun initVars() {
        if(context != null){
            client = LocationServices.getSettingsClient(context!!)
        }
    }

    fun getUserLocation() {
        initVars()
    }
}