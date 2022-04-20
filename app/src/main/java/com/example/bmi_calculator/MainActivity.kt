package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightGet = findViewById<EditText>(R.id.editTextWeight)
        val heightGet = findViewById<EditText>(R.id.editTextHeight)
        val calcButton = findViewById<Button>(R.id.btn_calculate)

        calcButton.setOnClickListener {
            if (weightGet.text.isEmpty() || heightGet.text.isEmpty()){
                incorrect()
                return@setOnClickListener
            }
            val weight = weightGet.text.toString().toFloat()
            val height = heightGet.text.toString().toFloat()
            if (height.toInt() == 0 || height.toInt() == 0) {
                incorrect()
                return@setOnClickListener
            }
            val bmi = weight / (height * height / 10000)
            val format = DecimalFormat("#.##")
            val result = format.format(bmi)
            val resultText = findViewById<TextView>(R.id.f1tv)
            val f2TV = findViewById<TextView>(R.id.f2tv)
            val f3TV = findViewById<TextView>(R.id.f3tv)
            f2TV.text = "Normal BMI is 18.5 – 24.9"
            var infoText = ""
            var color = R.color.black
            when {
                bmi < 18.5 -> {
                    infoText = "Underweight (Thin)"
                    color = R.color.purple_200
                }
                bmi in 18.5..24.9 ->{
                    infoText = "Normal weight"
                    color = R.color.green
                }
                else ->{
                    infoText = "Obesity"
                    color = R.color.red
                }
            }
            resultText.text = result
            f3TV.setTextColor(resources.getColor(color))
            f3TV.text = infoText
        }
    }
    private fun incorrect(){
        Toast.makeText(applicationContext, "incorrect input", Toast.LENGTH_SHORT).show()
    }
}