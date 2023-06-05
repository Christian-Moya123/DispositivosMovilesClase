package com.example.dispositivosmoviles.validator

import com.example.dispositivosmoviles.ui.activities.entity.LoginUser

class LoginValidation {
    fun checkLogin(name :String, password: String) :Boolean{

        val admin = LoginUser()
        return (admin.name == name && admin.pass == password)
    }
}