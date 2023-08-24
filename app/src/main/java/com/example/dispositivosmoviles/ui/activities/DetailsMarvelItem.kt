package com.example.dispositivosmoviles.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.databinding.ActivityDetailsMarvelItemBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class DetailsMarvelItem : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMarvelItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMarvelItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()

        //aqui recibimos los items de MarvelChars, pero ahora los tomamos como si fueran metadata Jikan
        val item = intent.getParcelableExtra<MarvelChars>("item")

        if (item !== null){
            binding.txtName.text = item.name
            binding.marveltitle.text = item.comic
            Picasso.get().load(item.image).into(binding.imgImage)
            binding.txtDescription.text = item.synopsis

        }

        binding.btnMeGusta.setOnClickListener {
            // Aquí puedes realizar directamente las acciones que necesitas

            // Por ejemplo, podrías hacer un registro o simplemente mostrar un mensaje
            val itemNombre = item?.name ?: "Nombre no disponible"
            val mensaje = "Se agregó '$itemNombre' a favoritos"

            Snackbar.make(
                binding.imgImage,
                mensaje,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}