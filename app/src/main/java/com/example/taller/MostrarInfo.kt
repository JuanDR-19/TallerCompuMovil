package com.example.taller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject

class MostrarInfo : AppCompatActivity() {

    private fun readJSON(): ArrayList<MainActivity.country> {
        val inputStream = resources.openRawResource(R.raw.paises)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val countries = json.getJSONArray("paises")
        val countriesList = ArrayList<MainActivity.country>()

        for (i in 0 until countries.length()) {
            val country = countries.getJSONObject(i)
            val capital = country.getString("capital")
            val countryName = country.getString("nombre_pais")
            val countryNameInt = country.getString("nombre_pais_int")
            val initials = country.getString("sigla")
            var obt = MainActivity.country(capital, countryName, countryNameInt, initials)
            countriesList.add(obt)
        }
        return countriesList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_info)

        val paises = readJSON()

        val texto: TextView =findViewById(R.id.textView3)

        val nombre = intent.getStringExtra("nombre")

        for (i in paises) {
            if (i.name == nombre) {
                texto.setText(i.toString())
            }
        }




    }
}