package com.example.dispositivosmoviles.ui.activities.activities2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivitySecondBinding
import com.example.dispositivosmoviles.ui.activities.fragment.FirstFragment
import com.example.dispositivosmoviles.ui.activities.fragment.SecondFragment
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {


    private lateinit var binding : ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("uce", "entrando a Oncrate")
    }

    override fun onStart(){
        super.onStart()
        //Log.d("uce", "entrando a Start")
        //init_Class()
        var name: String = " ";


        //POR EL MOMENTO COMENTADO///////////////////////////
        //intent.extras.let {

         //   name =it?.getString("var1")!!
        //}
        ////////////////////////////////////////////////////////////



       // binding.textView2S.text = "Que mas ve "+name
        Log.d("uce","Hola ${name}")
        binding.textView2S.text = "Bienvenido " + name
        init_Class()

    }

    private fun init_Class(){
        binding.button42S.setOnClickListener{
            //txtBuscar.text = "el evento se ha ejecutado"
           // binding.textView2S.text ="el codigo esta correctamente"

            var intent = Intent(
                this, login::class.java
            )

            startActivity(intent)



            /*
            var f = Snackbar.make(
                binding.button42S,
                "este es otro mensaje",
                Snackbar.LENGTH_LONG
            )
            var intent = Intent(
                this,MainActivity::class.java
            )

            startActivity(intent)


            f.setBackgroundTint(getColor(R.color.naranja)).show()*/
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inicio -> {
                    val frag = FirstFragment()
                    val transacction = supportFragmentManager.beginTransaction()
                    transacction.replace(binding.frmContainer.id, frag)
                    transacction.addToBackStack(null)//para crear una pila de navegacion
                    transacction.commit()
                    true
                }
                R.id.fav -> {

                    var suma :Int= 0
                    for(i in listOf(8,12,13)){
                        suma +=i
                    }

                    // Respond to navigation item 1 click
                    Snackbar.make(
                        binding.textView2S, "ENTRAMOS A INICIO ${suma}",
                        Snackbar.LENGTH_LONG
                    ).show()
                    true
                }

                R.id.chat_got -> {
                    val frag = SecondFragment()
                    val transacction = supportFragmentManager.beginTransaction()
                    transacction.replace(binding.frmContainer2.id, frag)
                    transacction.addToBackStack(null)//para crear una pila de navegacion
                    transacction.commit()
                    true
                }
                else -> false
            }
        }
    }
}