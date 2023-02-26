package com.example.taller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import org.json.JSONObject

class ListaDePaises : AppCompatActivity() {

    private fun intentInfo(pais: String){
        val intent = Intent(this,MostrarInfo::class.java)
        intent.putExtra("nombre",pais)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_paises)


        val spinner2: Spinner = findViewById(R.id.spinner2)
        val VerInfo: Button = findViewById(R.id.Info)

        val adapter = ArrayAdapter.createFromResource(this, R.array.Paises, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter



        VerInfo.setOnClickListener {
            val pais = spinner2.getSelectedItem().toString()
            intentInfo(pais)

        }




    }
}