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

    private fun intentCountry(){
        val intent = Intent(this,ListaDePaises::class.java)
        startActivity(intent)
    }

    private fun multiLangGreeting(language:String){
        Toast.makeText(this, language, Toast.LENGTH_LONG).show()
    }

    private fun spinnerSelection(lenguaje:String):String{

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
        buttonCountries.setOnClickListener {
            intentCountry()
        }
    }
}