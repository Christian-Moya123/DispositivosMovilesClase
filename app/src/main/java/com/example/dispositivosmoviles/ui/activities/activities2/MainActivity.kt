package com.example.dispositivosmoviles.ui.activities.activities2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.validator.LoginValidation
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    //conviuerte al activity main en un objeto
    @SuppressLint("MissingInflateId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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

            
            binding.button2.setOnClickListener{
                val check = LoginValidation().checkLogin(
                    binding.name.text.toString(),
                    binding.pass.text.toString()
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
                        binding.textPasword,"Correcto",
                        Snackbar.LENGTH_LONG).show()
                }
            }



        /*
            var f = Snackbar.make(
                binding.button2,
                "este es otro mensaje",
                Snackbar.LENGTH_LONG
            )


            f.setBackgroundTint(getColor(R.color.naranja)).show()*/
        }
}




