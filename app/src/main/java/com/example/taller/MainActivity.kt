package com.example.taller

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    data class Pais(
        val capital: String,
        val name: String,
        val name_int: String,
        val initials: String
    )
    private fun readJSON(): ArrayList<Pais> {
        val inputStream = resources.openRawResource(R.raw.paises)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val countries = json.getJSONArray("paises")
        val countriesList = ArrayList<Pais>()

        for (i in 0 until countries.length()) {
            val country = countries.getJSONObject(i)
            val capital = country.getString("capital")
            val countryName = country.getString("nombre_pais")
            val countryNameInt = country.getString("nombre_pais_int")
            val initials = country.getString("sigla")
            var obt = Pais(capital,countryName,countryNameInt,initials)
            countriesList.add(obt)
        }
        return countriesList
    }
    private fun changeintent(numtarg:Int, numberObtained:EditText){
        if(numtarg in 0..1000){
            //send the number to the next activity
            numberObtained.setText("")
            val intent = Intent(this, NumberGuessing::class.java)
            intent.putExtra("number", numtarg)
            startActivity(intent)
        }else{
            numberObtained.setText("")
            Toast.makeText(this, "Ingrese un numero menor o igual a 100 y mayor o igual a 0", Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numberObtained = findViewById<EditText>(R.id.number)
        val buttonSubmit = findViewById<Button>(R.id.button2)
        buttonSubmit.setOnClickListener{
            val result = numberObtained.text.toString()
            val numtarg = result.toInt()
            changeintent(numtarg,numberObtained)
        }
        val spinner: Spinner = findViewById(R.id.spinner)
        val countries = readJSON()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countries )
        spinner.adapter = adapter
    }
}