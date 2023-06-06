package com.example.dispositivosmoviles.ui.activities.activities2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.databinding.LoginBinding
import com.example.dispositivosmoviles.validator.LoginValidation
import com.google.android.material.snackbar.Snackbar

class login : AppCompatActivity()  {

    private lateinit var binding : LoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart(){
        super.onStart()
        init_Class()
    }

    private fun init_Class(){


        binding.buttonIngresar.setOnClickListener{
            val check = LoginValidation().checkLogin(
                binding.editTextName.text.toString(),
                binding.editTextPass.text.toString()
            )

            if(check){

                var intent = Intent(
                    this,SecondActivity::class.java
                )
                intent.putExtra("var1", "")

                intent.putExtra("var2", 2)

                startActivity(intent)



            }else{
                Snackbar.make(
                    binding.textView6,"Correcto",
                    Snackbar.LENGTH_LONG).show()
            }
        }
    }
}