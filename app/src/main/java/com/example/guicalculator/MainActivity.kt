package com.example.guicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.guicalculator.databinding.ActivityMainBinding
import androidx.core.widget.doOnTextChanged
import java.lang.IllegalArgumentException
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private var temp_val = ""
    private var val1 = 0.0
    private var val2 = 0.0
    private var operator = ""
    private var res = 0.0
    private lateinit var binding: ActivityMainBinding

    //used to handle any calculation errors
    private var errorMessage:Pair<Boolean, String> = Pair(false, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Value for first input through keyboard input
        binding.apply {
            input.doOnTextChanged { text, _, _, _ ->
                temp_val= text.toString()
            }
        }
        //Clear button
        binding.clearButton.setOnClickListener{ view ->
            clear()
        }

        //Numeric button input
        binding.zeroButton.setOnClickListener {view ->
            val newText:String = temp_val + "0"
            binding.input.setText(newText)
        }
        binding.oneButton.setOnClickListener {view ->
            val newText:String = temp_val + "1"
            binding.input.setText(newText)
        }

        binding.twoButton.setOnClickListener {view ->
            val newText:String = temp_val + "2"
            binding.input.setText(newText)
        }

        binding.threeButton.setOnClickListener {view ->
            val newText:String = temp_val + "3"
            binding.input.setText(newText)
        }

        binding.fourButton.setOnClickListener {view ->
            val newText:String = temp_val + "4"
            binding.input.setText(newText)
        }

        binding.fiveButton.setOnClickListener {view ->
            val newText:String = temp_val + "5"
            binding.input.setText(newText)
        }

        binding.sixButton.setOnClickListener {view ->
            val newText:String = temp_val + "6"
            binding.input.setText(newText)
        }

        binding.sevenButton.setOnClickListener {view ->
            val newText:String = temp_val + "7"
            binding.input.setText(newText)
        }

        binding.eightButton.setOnClickListener {view ->
            val newText:String = temp_val + "8"
            binding.input.setText(newText)
        }

        binding.nineButton.setOnClickListener {view ->
            val newText:String = temp_val + "9"
            binding.input.setText(newText)
        }

        //Operator Buttons
        binding.plusButton.setOnClickListener{view ->
            if (temp_val != ""){
                val1 =  temp_val.toDouble()
                operator = "+"
                clear()
                Log.d("MainActivity", "Operator: " + operator + " ,Val1: " + val1.toString())
            }
        }
        binding.minusButton.setOnClickListener{view ->
            if (temp_val != ""){
                val1 =  temp_val.toDouble()
                operator = "-"
                clear()
                Log.d("MainActivity", "Operator: " + operator + " ,Val1: " + val1.toString())
            }
        }
        binding.multiplyButton.setOnClickListener{view ->
            if (temp_val != ""){
                val1 =  temp_val.toDouble()
                operator = "*"
                clear()
                Log.d("MainActivity", "Operator: " + operator + " ,Val1: " + val1.toString())
            }
        }

        binding.divideButton.setOnClickListener{view ->
            if (temp_val != ""){
                val1 =  temp_val.toDouble()
                operator = "/"
                clear()
                Log.d("MainActivity", "Operator: " + operator + " ,Val1: " + val1.toString())
            }
        }

        binding.moduloButton.setOnClickListener{view ->
            if (temp_val != ""){
                val1 =  temp_val.toDouble()
                operator = "%"
                clear()
                Log.d("MainActivity", "Operator: " + operator + " ,Val1: " + val1.toString())
            }
        }

        binding.sqrtButton.setOnClickListener{view ->
            if (temp_val != ""){
                val1 =  temp_val.toDouble()
                operator = "sqrt"
                calculate(operator)
                if (errorMessage != Pair(false, "")) {
                    Toast.makeText(
                        this,
                        errorMessage.second,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    if (res % 1.0 == 0.0) {
                        res.toInt()
                        Log.d("MainActivity", res.toInt().toString())
                        binding.input.setText(res.toInt().toString())
                    }
                    else {
                        binding.input.setText(res.toString())
                    }
                }
                Log.d("MainActivity", "Operator: " + operator + " ,Val1: " + val1.toString())

            }
        }

        binding.signButton.setOnClickListener{view ->
            if (temp_val != ""){
                if (temp_val.contains(".")) {
                    val neg_double = temp_val.toDouble() * -1.0
                    temp_val = neg_double.toString()
                    binding.input.setText(temp_val)
                }
                else{
                    val neg_int = temp_val.toInt() * -1
                    temp_val = neg_int.toString()
                    binding.input.setText(temp_val)
                }
            }
            else {
                Toast.makeText(
                    this,
                    "Missing value",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        binding.equalButton.setOnClickListener{view ->
            if (temp_val != ""){
                val2 = temp_val.toDouble()
                calculate(operator)
                if (errorMessage != Pair(false, "")) {
                    Toast.makeText(
                        this,
                        errorMessage.second,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    if (res % 1.0 == 0.0) {
                        res.toInt()
                        Log.d("MainActivity", res.toInt().toString())
                        binding.input.setText(res.toInt().toString())
                    }
                    else {
                        binding.input.setText(res.toString())
                    }
                }
            }
        }
    }
    private fun clear(){
        binding.input.setText("")
        res = 0.0
    }

    private fun calculate(operator:String) {

        if (operator == "/" || operator == "%" || operator == "" || operator == "sqrt") {
            when(operator) {
                "/" ->
                    if (val2 == 0.0) {
                        Log.d("MainActivity","Cannot divide by zero")
                        errorMessage = Pair(true, "Cannot divide by zero")
                    }
                    else {
                        res = val1 / val2
                    }
                "%" ->
                    if (val2 == 0.0) {
                        Log.d("MainActivity","Cannot divide by zero")
                        errorMessage = Pair(true, "Cannot divide by zero")
                    }
                    else {
                        res = val1 % val2
                    }
                "" -> {
                    Log.d("MainActivity","Missing operator")
                    errorMessage = Pair(true, "Missing operator")
               }
                "sqrt" -> {
                    if (val1 >= 0) {
                        res = sqrt(val1)
                    }
                    else {
                        Log.d("MainActivity", "Cannot take the square root of a negative number")
                        errorMessage = Pair(true, "Cannot take the square root of a negative number")
                    }
                }
            }

        }
        else {
            res = when (operator) {
                "+" -> val1 + val2
                "-" -> val1 - val2
                "*" -> val1 * val2
                else -> throw IllegalArgumentException("Unsupported operator: $operator")
            }
        }
    }
}