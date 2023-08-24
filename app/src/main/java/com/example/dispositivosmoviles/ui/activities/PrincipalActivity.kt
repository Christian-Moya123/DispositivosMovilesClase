package com.example.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.data.entities.marvel.characters.adapters.MarvelAdapter
import com.example.dispositivosmoviles.databinding.ActivityPrincipalBinding
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.logic.data.getMarvelCharsDB
import com.example.dispositivosmoviles.logic.marvelLogic.MarvelLogic


import com.example.dispositivosmoviles.ui.fragments.FirstFragment
import com.example.dispositivosmoviles.ui.fragments.SecondFragment
import com.example.dispositivosmoviles.ui.fragments.ThirdFragment
import com.example.dispositivosmoviles.ui.utilities.DispositivosMoviles
import com.example.dispositivosmoviles.ui.utilities.FragmentsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrincipalActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {

        val animacion1 = AnimationUtils.loadAnimation(this, R.anim.animacion1)
        val animacion2 = AnimationUtils.loadAnimation(this, R.anim.animacion2)
        val animacion3 = AnimationUtils.loadAnimation(this, R.anim.animacion3)

        super.onStart()
        var name: String = " "


        Log.d("UCE", "Hola ${name}")
//        binding.txtTitle.text = "Bienvenido " + name.toString()
//
        binding.btnCamara.setOnClickListener {
            binding.txtTitle.startAnimation(animacion1)
            startActivity(Intent(this, CameraActivity::class.java))
        }

        binding.btnNotificacion.setOnClickListener {
            binding.txtTitle.startAnimation(animacion2)
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        binding.btnProgreso.setOnClickListener {
            binding.txtTitle.startAnimation(animacion3)
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        binding.btnMeme.setOnClickListener {
            binding.txtTitle.startAnimation(animacion3)
            startActivity(Intent(this, MemeActivity::class.java))
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_wifi -> {
                    FragmentsManager().replaceFragment(supportFragmentManager, binding.frmContainter.id, FirstFragment())
                    true
                }

                R.id.menu_item_bluetooth -> {
                    FragmentsManager().replaceFragment(supportFragmentManager, binding.frmContainter.id, SecondFragment())
                    true
                }
                R.id.menu_item_settings -> { FragmentsManager().replaceFragment(supportFragmentManager, binding.frmContainter.id, ThirdFragment())
                    true
                }



                else -> false
            }
        }

    }
    override fun onBackPressed(){
        super.onBackPressed();
    }





}