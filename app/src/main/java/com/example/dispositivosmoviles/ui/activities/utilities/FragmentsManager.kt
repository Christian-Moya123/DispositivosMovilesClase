package com.example.dispositivosmoviles.ui.activities.utilities

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentsManager {

    fun replaceFrangment(
        manager: FragmentManager,
        container: Int,
        fragment: Fragment
    ){

        with(manager.beginTransaction()){
            replace(container, fragment)
            commit()
        }
    }

    fun addFrangment(
        manager: FragmentManager,
        container: Int,
        fragment: Fragment
    ){

        with(manager.beginTransaction()){

            add(container, fragment)
            commit()
        }
    }

}