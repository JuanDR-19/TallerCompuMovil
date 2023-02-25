package com.example.taller

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject



class MainActivity : AppCompatActivity() {

    data class country( //countries class
        val capital: String,
        val name: String,
        val name_int: String,
        val initials: String
    )
    private fun readJSON(): ArrayList<country> {
        val inputStream = resources.openRawResource(R.raw.paises)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val countries = json.getJSONArray("paises")
        val countriesList = ArrayList<country>()

        for (i in 0 until countries.length()) {
            val country = countries.getJSONObject(i)
            val capital = country.getString("capital")
            val countryName = country.getString("nombre_pais")
            val countryNameInt = country.getString("nombre_pais_int")
            val initials = country.getString("sigla")
            var obt = country(capital,countryName,countryNameInt,initials)
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

    private fun multiLangGreeting(language:String){
        //TODO
        /*
        Create a code for launching a toast greeting the user in the languaje selected in the spinner
         */
        Toast.makeText(this, language, Toast.LENGTH_LONG).show()
    }

    private fun spinnerSelection(lenguaje:String):String{
        //TODO
        //get the arguments selected in the spinner and return a string value that will go as an argument
        //in the multiLandGreeting fun
        var mensaje:String = "prueba"
        if(lenguaje == "Spanish"){
            mensaje = getString(R.string.Spanish)
        }
        else if(lenguaje == "English"){
            mensaje = getString(R.string.English)
        }
        else if(lenguaje == "French"){
            mensaje = getString(R.string.French)
        }
        else if(lenguaje == "Japanese"){
            mensaje = getString(R.string.Japanese)
        }
        else if(lenguaje == "Portuguese"){
            mensaje = getString(R.string.Portuguese)
        }
        return mensaje;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numberObtained = findViewById<EditText>(R.id.number)
        val buttonSubmit = findViewById<Button>(R.id.button2)
        val buttonGreeting = findViewById<Button>(R.id.random)
        val spinner: Spinner = findViewById(R.id.spinner)
        val buttonCountries = findViewById<Button>(R.id.countries)
        buttonSubmit.setOnClickListener{
            val result = numberObtained.text.toString()
            val numtarg = result.toInt()
            changeintent(numtarg,numberObtained)
        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.Languages, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        buttonGreeting.setOnClickListener{
            var lenguaje = spinner.getSelectedItem().toString()
            multiLangGreeting(spinnerSelection(lenguaje))
        }
    }
}