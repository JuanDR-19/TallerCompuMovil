package com.example.taller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

fun randNum(Range: Int): Int {
    return Random.nextInt(Range + 1) // 0 inclusive
}

class NumberGuessing : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guessing)

        var counter = 0
        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        val rangeText = findViewById<TextView>(R.id.range)
        val counterText = findViewById<TextView>(R.id.counter)
        val messageField = findViewById<TextView>(R.id.message)
        val numtarg = intent.getIntExtra("number", 0)
        var randomNumber = randNum(numtarg)

        rangeText.append(" $numtarg")
        counterText.append(" $counter")

        buttonPlay.setOnClickListener{
            val entryField = findViewById<TextView>(R.id.entry)
            val entryNumber = findViewById<TextView>(R.id.entry).text.toString().toInt()

            if(entryNumber != randomNumber){
                entryField.text=""
                counter++
                counterText.text = "Counter: $counter"
                if(entryNumber > randomNumber){
                    messageField.text= "Message: $entryNumber is bigger"
                }else{
                    messageField.text= "Message: $entryNumber is smaller"
                }
            } else {
                counter++
                counterText.text = "Counter: $counter"
                entryField.text=""
                Toast.makeText(this, "Felicidades, consiguió adivinar el número en $counter intentos", Toast.LENGTH_LONG).show()
                messageField.text= "Message: El valor $entryNumber es el número que tenia que adivinar"
                randomNumber = randNum(numtarg)
            }
        }
    }
}