package com.example.dispositivosmoviles.ui.activities.activities2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dispositivosmoviles.databinding.ActivityDetailsMarvelItemBinding
import com.example.dispositivosmoviles.ui.activities.entity.jkanmarvel.LoginMarvel
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

       /* var name:String?=""
        intent.extras?.let{
            name = it.getString("name")

        }
        if (!name.isNullOrEmpty()){
            binding.txtName.text=name
        }
        */
        val item = intent.getParcelableExtra<LoginMarvel>("name")
        if (item !== null){
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic
            Picasso.get().load(item.imagenes).into(binding.imgMarvel)

        }

    }
}