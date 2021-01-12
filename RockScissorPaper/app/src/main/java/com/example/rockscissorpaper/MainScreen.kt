package com.example.rockscissorpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)




        //buttons
        val rockbtn = findViewById<ImageButton>(R.id.rock)
        val paperbtn = findViewById<ImageButton>(R.id.paper)
        val scissorbtn = findViewById<ImageButton>(R.id.scissor)
        val resetbtn = findViewById<Button>(R.id.reset)

        //changing text
        var UserChoice = findViewById<TextView>(R.id.UserC)
        var RandomChoice = findViewById<TextView>(R.id.AiC)
        var resultText = findViewById<TextView>(R.id.result)



        rockbtn.setOnClickListener {
            UserChoice.text = "input: Rock"
            Log.d("result", "Rock")
            val Rchoice = randomGenerater()
            RandomChoice.text = "Opponent: $Rchoice"

            //result
            when (Rchoice){
                "Scissor" -> {
                    resultText.text = "Win"
                }
                "Rock" ->{
                    resultText.text = "Draw"
                }
                "Paper" ->{
                    resultText.text = "Lost"
                }
            }
        }
        paperbtn.setOnClickListener {
            UserChoice.text = "Input: Paper"
            Log.d("result", "Paper")
            val Rchoice = randomGenerater()
            RandomChoice.text = "Opponent: $Rchoice"

            //result
            when (Rchoice){
                "Scissor" -> {
                    resultText.text = "Lost"
                }
                "Rock" ->{
                    resultText.text = "Win"
                }
                "Paper" ->{
                    resultText.text = "Draw"
                }
            }

        }
        scissorbtn.setOnClickListener {
            UserChoice.text = "Input: Scissor"
            Log.d("result", "Scissor")
            val Rchoice = randomGenerater()
            RandomChoice.text = "Opponent: $Rchoice"

            //result
            when (Rchoice){
                "Scissor" -> {
                    resultText.text = "Draw"
                }
                "Rock" ->{
                    resultText.text = "Lost"
                }
                "Paper" ->{
                    resultText.text = "Win"
                }
            }


        }
        resetbtn.setOnClickListener {

        }

    }
}
//Random Choice changing



//Random Choice
fun randomGenerater(): String {
    var result: String = ""
    var randomNumber: Int = (1..9).random()
    println(randomNumber)
    when (randomNumber) {
        1, 4, 7 -> {
            result = "Rock"
        }
        2, 5, 8 -> {
            result = "Paper"
        }
        3, 6, 9 -> {
            result = "Scissor"
        }
    }
    return result

}

