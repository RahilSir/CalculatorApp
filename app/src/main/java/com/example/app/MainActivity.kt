package com.example.app  // Ensure this matches your project's package name



import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentInput = ""
    private var operator: String? = null
    private var firstNumber: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
    }

    fun onNumberClick(view: View) {
        val button = view as Button
        currentInput += button.text
        display.text = currentInput
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            operator = button.text.toString()
            currentInput = ""
            display.text = operator
        }
    }

    fun onEquals(view: View) {
        if (firstNumber != null && currentInput.isNotEmpty()) {
            val secondNumber = currentInput.toDouble()
            val result = when (operator) {
                "+" -> firstNumber!! + secondNumber
                "-" -> firstNumber!! - secondNumber
                "*" -> firstNumber!! * secondNumber
                "/" -> if (secondNumber != 0.0) firstNumber!! / secondNumber else "Error"
                else -> "Error"
            }
            display.text = result.toString()
            currentInput = result.toString()
            firstNumber = null
            operator = null
        }
    }

    fun onClear(view: View) {
        currentInput = ""
        firstNumber = null
        operator = null
        display.text = "0"
    }
}
