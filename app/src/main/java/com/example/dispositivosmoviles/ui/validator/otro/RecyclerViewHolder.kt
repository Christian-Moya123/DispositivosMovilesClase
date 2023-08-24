package com.example.dispositivosmoviles.ui.validator.otro

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val view: TextView = itemView.findViewById(R.id.randomText)

    fun getView(): TextView {
        return view
    }
}