package com.practice.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.practice.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            val weight = binding.etWeight.text.toString()
            val height = binding.etHeight.text.toString()

            if(validateInput(weight, height)){
                val bmi = weight.toFloat()/((height.toFloat()/100) * (height.toFloat()/100))
                //get result in two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }

    }

    private fun validateInput(weight: String?, height: String?) : Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight cannot be empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Height cannot be empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "Normal range is 18.5 - 24.5"

        var resultText = ""
        var color = 0

        when {
            bmi < 18.5 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.99 -> {
                resultText = "Healthy"
                color = R.color.healthy
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.Obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText

    }


}