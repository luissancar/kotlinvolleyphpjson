package com.example.volleykotlin

import android.app.VoiceInteractor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  conectarSimple()
        conectarJson()
    }

    fun conectarJson(){  // conecta con una url y devuelve su contenido
        val url="http://iesayala.ddns.net/json/jsonguitarras2.php"
        val queue=Volley.newRequestQueue(this)
        val stringRequest=StringRequest(Request.Method.GET,url,Response.Listener { response ->
            //conectó correctamente
            val jsonArray=JSONArray(response)
            for (i in 0 until jsonArray.length()){
                val jsonObject=JSONObject(jsonArray.getString(i))
                val marca=jsonObject.get("marca")
                val modelo=jsonObject.get("modelo")
                resultado.text=resultado.text.toString()+marca+" "+modelo+"\n"}


        },Response.ErrorListener {
            resultado.text="Error"   // se produjo un error
        })

        queue.add(stringRequest)
    }






    fun conectarSimple(){  // conecta con una url y devuelve su contenido
        val url="https://www.google.com"
        val queue=Volley.newRequestQueue(this)
        val stringRequest=StringRequest(Request.Method.GET,url,Response.Listener { response ->
            resultado.text="la respuesta es ${response}"  //conectó correctamente
        },Response.ErrorListener {
            resultado.text="Error"   // se produjo un error
        })

        queue.add(stringRequest)
    }

}