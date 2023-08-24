package com.example.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dispositivosmoviles.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class GptActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpt)
        val etQuestion  = findViewById<EditText>(R.id.txtQuestion)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val txtResponse = findViewById<TextView>(R.id.txtResponse)

        btnSubmit.setOnClickListener{
            val question = etQuestion.text.toString()
            Toast.makeText(this, question, Toast.LENGTH_SHORT).show()
            getResponse(question){ response ->
                runOnUiThread{
                    txtResponse.text=response
                }

            }
        }

    }

    fun getResponse(question: String, callback: (String) -> Unit){
        val apiKey="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"
        val url = "https://api.opnai.com/v1/completions"

        val requestBody = """ 
            {
             "model": "text-davinci-003",
             "prompt": "$question",
             "max_tokens": 500,
             "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "API failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if(body !=null){
                    Log.v("data", body)
                }
                else{
                    Log.v("data","empy")
                }
                var JsonObject = JSONObject(body)
                val jsonArray:JSONArray = JsonObject.getJSONArray("choices")
                val textResult = jsonArray.getJSONObject(0).getString("text")
                callback(textResult)
            }

        })
    }
}