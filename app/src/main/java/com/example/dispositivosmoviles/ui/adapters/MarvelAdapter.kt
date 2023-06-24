package com.example.dispositivosmoviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.MarvelCharacterssBinding
import com.example.dispositivosmoviles.ui.activities.entity.LoginMarvel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MarvelAdapter (private val items: List<LoginMarvel>,
                     private var fnClick: (LoginMarvel) -> Unit
): RecyclerView.Adapter<MarvelAdapter.MarlverViewHolder>() {



    class MarlverViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding : MarvelCharacterssBinding = MarvelCharacterssBinding.bind(view)

        fun render(item : LoginMarvel, fnClick: (LoginMarvel) -> Unit){
            println("recibiendo a ${item.name}")
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic

            Picasso.get().load(item.imagenes).into(binding.imgMarvel)

            itemView.setOnClickListener{
                fnClick(item)
            }



                //Snackbar.make(binding.imgMarvel, item.name, Snackbar.LENGTH_SHORT).show()

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapter.MarlverViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MarlverViewHolder(
            inflater.inflate(
                R.layout.marvel_characterss,
                parent, false))

    }

    override fun onBindViewHolder(holder: MarvelAdapter.MarlverViewHolder, position: Int) {
        holder.render(items[position], fnClick)
    }

    override fun getItemCount(): Int =  items.size

}

