package com.example.taller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numberObtained = findViewById<EditText>(R.id.number)
        val buttonSubmit = findViewById<Button>(R.id.button2)
        buttonSubmit.setOnClickListener{
            val result = numberObtained.text.toString()
            val numtarg = result.toInt()
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
    }
}