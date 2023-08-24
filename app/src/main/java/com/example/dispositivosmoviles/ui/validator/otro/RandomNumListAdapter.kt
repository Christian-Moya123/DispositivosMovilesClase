package com.example.dispositivosmoviles.ui.validator.otro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import kotlin.random.Random

class RandomNumListAdapter(seed: Int) : RecyclerView.Adapter<RecyclerViewHolder>() {
    private val random = Random(seed)

    override fun getItemViewType(position: Int): Int {
        return R.layout.frame_textview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.getView().text = random.nextInt().toString()
    }

    override fun getItemCount(): Int {
        return 100
    }
}