package com.example.dispositivosmoviles.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityPogressBinding
import com.example.dispositivosmoviles.ui.viewmodel.ProgressViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PogressActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityPogressBinding

    private val progressviewmodel by viewModels<ProgressViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPogressBinding.inflate(layoutInflater)
        setContentView(binding.root)

    // LiveData
        progressviewmodel.progressState.observe(this, Observer{
           binding.progressBar.visibility = it
        })

        progressviewmodel.items.observe(this, Observer {
            Toast.makeText(this,it[10].name, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, NotificacionActivity::class.java))
        })


        //LIsteners: jecuta precesos del viewmodel
        binding.btnProseso.setOnClickListener {
            progressviewmodel.processBackground(3000)

        }

        binding.btnProseso2.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                progressviewmodel.getMarvelChar(0,90)
            }


        }


    }
}