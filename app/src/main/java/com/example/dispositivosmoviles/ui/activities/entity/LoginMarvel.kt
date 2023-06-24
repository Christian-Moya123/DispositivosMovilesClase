package com.example.dispositivosmoviles.ui.activities.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LoginMarvel (
    val id:Int,
    val name :String,
    val comic: String,
    val imagenes: String
    ): Parcelable
{
}