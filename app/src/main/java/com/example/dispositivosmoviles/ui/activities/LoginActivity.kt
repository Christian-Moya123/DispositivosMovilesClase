package com.example.dispositivosmoviles.ui.activities


import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker.PermissionResult
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

import androidx.lifecycle.lifecycleScope
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.ui.utilities.MyLocationManager
import com.example.dispositivosmoviles.ui.validator.LoginValidator
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //interfaz que nos va a permitir acceder a la ubicacion del ususario
    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private lateinit var locationRequest : LocationRequest
    private lateinit var locationCallback : LocationCallback
    private lateinit var client: SettingsClient
    private lateinit var locationSettingsRequest : LocationSettingsRequest

    private var currentLocation : Location? = null

    private val speechToText = registerForActivityResult(StartActivityForResult()){
            activityResult ->

        val sn = Snackbar.make(
            binding.txtName,
            "",
            Snackbar.LENGTH_LONG
        )

        var message = ""

        when(activityResult.resultCode){
            RESULT_OK -> {
                val msg = activityResult.
                data?.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )?.get(0).toString()

                if(msg.isNotEmpty()){
                    val intent = Intent(
                        Intent.ACTION_WEB_SEARCH
                    )
                    intent.setClassName(
                        "com.google.android.googlequicksearchbox",
                        "com.google.android.googlequicksearchbox.SearchActivity"
                    )
                    intent.putExtra(SearchManager.QUERY, msg)
                    startActivity(intent)
                }
            }
            RESULT_CANCELED -> {
                message = "Proceso cancelado"
                sn.setBackgroundTint(resources.getColor(R.color.red))
            }
            else -> {
                "Ocurrio un error"
                sn.setBackgroundTint(resources.getColor(R.color.red))
            }

        }
    }

    @SuppressLint("MissingPermission")
    private val locationContract = registerForActivityResult(RequestPermission()) {
            isGranted ->
        when(isGranted){
            true -> {
                client.checkLocationSettings(locationSettingsRequest).apply {
                    //si el GPs esta funcionando:
                    addOnSuccessListener {
                        val task = fusedLocationProviderClient.lastLocation
                        task.addOnSuccessListener {
                                location ->

                            fusedLocationProviderClient.requestLocationUpdates(
                                locationRequest, //tipo ubicacion, tiempo
                                locationCallback, //resultado
                                Looper.getMainLooper() //loop
                            )
                        }
                    }

                    //si el GPS falla
                    addOnFailureListener {
                            ex ->
                        //si es una excepcion que la API puede solucionar
                        if(ex is ResolvableApiException) {
                            //lanza alert dialog listo para habilitar el GPS
                            ex.startResolutionForResult(
                                this@MainActivity,
                                LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                            )
                        }
                    }

                }

                //cuando falla
               /* task.addOnFailureListener{
                    val alert = AlertDialog.Builder(this)
                    alert.apply {
                        setTitle("Alerta")
                        setMessage("Existe un problema con el sistema de posicionamiento global en el sistema")
                        setPositiveButton("Ok") {dialog, id ->
                            dialog.dismiss()
                        }
                        setNegativeButton("Cancelar") {dialog, id ->
                            dialog.dismiss()
                        }
                        setCancelable(false) //no puede tocar fuera el dialog hasta que toque alguna opcion
                    }.create()
                    alert.show()
                }*/

            }

            //Informa al usuario de porque se necesita los permisos
            shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                Snackbar.make(binding.txtName,
                    "Ayude con el permiso",
                    Snackbar.LENGTH_LONG)
                    .show()
            }
            false -> {
                Snackbar.make(binding.txtName, "Permiso denegado", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        )
            //.setMaxUpdates(3) //cuantas veces se va a pedir la actu
            .build() //exactitud de ubicacion y tiempo en ms
        locationCallback = object : LocationCallback() {
            //Ctrl + O, override
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                if(locationResult != null){
                    locationResult.locations.forEach{
                            location ->
                        currentLocation = location
                        Log.d("UCE",
                            "Ubicacion: ${location.latitude}, " +
                                    "${location.longitude}")
                    }
                }

            }
        }
        client = LocationServices.getSettingsClient(this)
        locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest).build()

    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    //Inyeccion de dependencias


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        //Para detener la obtencion de ubicacion al cambiar de activity
        fusedLocationProviderClient.removeLocationUpdates(
            locationCallback)
    }

    private fun initClass() {
        binding.btnLogin.setOnClickListener {

            val check = LoginValidator().checkLogin(
                binding.txtName.text.toString(), binding.txtPass.text.toString()
            )

            if(check){
                lifecycleScope.launch(Dispatchers.IO){
                    saveDataStore(binding.txtName.text.toString())
                }

                //Intent explicito
                var intent = Intent(this, PrincipalActivity::class.java)

                intent.putExtra("var1", binding.txtName.text.toString())
                intent.putExtra("var2", 2)
                startActivity(intent)
            }else{
                var snackbar = Snackbar.make(binding.btnSignup,
                    "Usuario o contraseña inválidos",
                    Snackbar.LENGTH_LONG)
                //snackbar.setBackgroundTint(ContextCompat.getColor(binding.root.context, R.color.principal_color_dm))
                snackbar.setBackgroundTint(getResources().getColor(R.color.black))
                snackbar.show()
            }
        }



        binding.btnTwitter.setOnClickListener {

            locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)

            /*val intent = Intent(
                Intent.ACTION_WEB_SEARCH
            )
            //abre la barra de busqueda de Google
            intent.setClassName(
                "com.google.android.googlequicksearchbox",
                "com.google.android.googlequicksearchbox.SearchActivity"
            )
            //busca steam en el navegador
            intent.putExtra(SearchManager.QUERY, "steam")
            startActivity(intent)*/
        }

        val appResultLocal = registerForActivityResult(StartActivityForResult()) {
                resultActivity ->

            val sn = Snackbar.make(
                binding.txtName,
                "",
                Snackbar.LENGTH_LONG
            )

            //contrato con las clausulas a ejecutar
            var message = when(resultActivity.resultCode) {
                RESULT_OK -> {
                    sn.setBackgroundTint(resources.getColor(R.color.blue))
                    resultActivity.data?.getStringExtra("result")
                        .orEmpty() //si no hay nada, devuelve vacio
                }
                RESULT_CANCELED -> {
                    sn.setBackgroundTint(resources.getColor(R.color.red))
                    resultActivity.data?.getStringExtra("result")
                        .orEmpty()
                }
                else -> {
                    "Dudoso"
                }
            }
            sn.setText(message)
            sn.show()
        }

        binding.btnFacebook.setOnClickListener {

            val intentSpeech = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intentSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM //modelo de lenguaje libre
            )
            intentSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE, //en que idioma va a hablar
                Locale.getDefault() //toma el lenguaje del dispositivo
            )
            intentSpeech.putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "Di algo..."
            )
            speechToText.launch(intentSpeech)

            /*val resIntent = Intent(this, ResultActivity::class.java)
            appResultLocal.launch(resIntent)*/
        }

    }

    private suspend fun saveDataStore(stringData: String) {
        dataStore.edit{ prefs ->
            prefs[stringPreferencesKey("usuario")] = stringData
            //UUID: Universal Unique Identifier
            prefs[stringPreferencesKey("session")] = java.util.UUID.randomUUID().toString()
            prefs[stringPreferencesKey("email")] = "dispositivosmoviles@uce.edu.ec"
        }
    }

    private fun test(){
        var location = MyLocationManager(this)
        location.getUserLocation()
    }

}