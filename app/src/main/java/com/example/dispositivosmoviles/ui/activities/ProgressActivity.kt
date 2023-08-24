package com.example.dispositivosmoviles.ui.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.dispositivosmoviles.databinding.ActivityPogressBinding
import com.example.dispositivosmoviles.ui.viewmodels.ProgressViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//MVVM
class ProgressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPogressBinding

    //progreso vista modelo de vistas modelos.
    private val progressViewModel by viewModels<ProgressViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPogressBinding.inflate(layoutInflater)
        setContentView(binding.root)


        progressViewModel.progressState.observe(this, Observer {
            binding.progressBar.visibility = it //cada vez que hay un cambio se ejecuta.
        })

        progressViewModel.items.observe(this, Observer {

            startActivity(Intent(this, GptActivity::class.java))
        })


        binding.btnProseso.setOnClickListener {
            //progressViewModel.processBackground(3000)
            lifecycleScope.launch(Dispatchers.IO) {
                progressViewModel.getMarvelChars(0, 90)
            }
        }

        binding.btnProseso2.setOnClickListener {
            Snackbar.make(binding.root, "Botón equivocado", Snackbar.LENGTH_SHORT).show()

        }
    }
}