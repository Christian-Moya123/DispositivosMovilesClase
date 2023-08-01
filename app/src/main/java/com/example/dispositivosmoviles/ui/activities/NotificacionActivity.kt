package com.example.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityNotificacionBinding
import com.example.dispositivosmoviles.ui.utilities.BroadCasterNotifications
import java.util.Calendar

class NotificacionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {
            createNotificationChannel()
            sendNotification()

        }

        binding.btnNotificationProgramada.setOnClickListener{

            val calendar  = Calendar.getInstance()
            val hora = binding.timePicker.hour
            val minutes = binding.timePicker.minute
            Toast.makeText(this,"La notificacion se activara  las Hora Es: $hora con $minutes ",Toast.LENGTH_LONG).show()
            calendar.set(Calendar.HOUR, hora)
            calendar.set(Calendar.MINUTE, minutes)
            calendar.set(Calendar.SECOND, 0)
            sendNorificacionTimePicker(calendar.timeInMillis)

        }


    }


    private fun sendNorificacionTimePicker(time: Long) {
        val myIntent = Intent(applicationContext, BroadCasterNotifications::class.java)
        val myPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            myIntent,
            // formas de mostrar cuando se ejecuta el intent (FLAGs)
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, myPendingIntent)
    }


    val CHANNEL : String = "Notificaciones"

    private fun createNotificationChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Variedades"
            val descriptionText = "Notificaciones simples de variedades"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNotification() {
        //crea la notificacion
        val noti = NotificationCompat.Builder(this, CHANNEL)
        noti.setContentTitle("Primera notificacion")
        noti.setContentText("Tienes una notificacion")
        noti.setSmallIcon(R.drawable.twitter)
        noti.setPriority(NotificationCompat.PRIORITY_HIGH)
        noti.setStyle(NotificationCompat.BigTextStyle()
            .bigText("Esta es una notificacion para recordar que estamos trabajando en Android"))

        //envia la notificacion
        with(NotificationManagerCompat.from(this)){
            notify(1, noti.build())
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNorificacion() {

        val intent = Intent(this, CamaraActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val noti = NotificationCompat.Builder(this, CHANNEL)
        //cuerpo de Notificacion
        noti.setContentTitle("Titulo")
        noti.setContentText("No te olvides de parpadear ni de tomar awita <3")
        noti.setSmallIcon(R.drawable.twitter)
        //prioridad de la notificacion
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        noti.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line...")
        )
        //aqui se toca la notificacion.
        noti.setContentIntent(pendingIntent)
        noti.setAutoCancel(true)


        //eniar la notificacion
        with(NotificationManagerCompat.from(this)){
            notify(1,noti.build())
        }

    }
}