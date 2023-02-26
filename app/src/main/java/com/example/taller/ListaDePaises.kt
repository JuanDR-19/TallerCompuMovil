package com.example.taller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import org.json.JSONObject

class ListaDePaises : AppCompatActivity() {

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
        setContentView(R.layout.activity_lista_de_paises)

        val paises = readJSON()

        val spinner2: Spinner = findViewById(R.id.spinner2)
        val informacion: TextView = findViewById(R.id.textView2)
        val VerInfo: Button = findViewById(R.id.Info)

        val adapter = ArrayAdapter.createFromResource(this, R.array.Paises, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter

        VerInfo.setOnClickListener {
            val pais = spinner2.getSelectedItem().toString()

            for (i in paises){
                if(i.name ==pais){
                    informacion.text = i.toString()
                }
            }

        }




    }
}