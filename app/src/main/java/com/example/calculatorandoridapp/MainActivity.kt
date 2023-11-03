package com.example.calculatorkotlinapp

import android.icu.text.DecimalFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    //variable declarations
    lateinit var calculation: EditText
    lateinit var result: EditText
    var calc: String =""
    var res: String =""


    lateinit var btn_ac: Button
    lateinit var btn_backspace: Button
    lateinit var btn0: Button
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button
    lateinit var subtract: Button
    lateinit var add: Button
    lateinit var divide: Button
    lateinit var multiply: Button
    lateinit var equalsto: Button
    lateinit var decimal: Button
    var operator:String =""
    var parts:String = ""
    var num1: Float = 0.0F
    var num2: Float = 0.0F
    var result_num: Float = 0.0F
    var button_state1: Boolean =true        //consective operators not allowed
    var check: Boolean = true               //no results when calculations end with an operator

    fun calculation_disp(calc:String) {
        calculation.setText(calc)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun result_disp(res1:String) {

        var res2 =res1
        if (calc.endsWith("+"))
            check=false
        else if (calc.endsWith("-"))
            check=false
        else if (calc.endsWith("/"))
            check=false
        else if (calc.endsWith("*"))
            check=false

        if (res2.endsWith(".0") and check==true) { // Check if the decimal portion ends with ".0"
            res2 = res2.substring(0, res2.length - 2)
            result.setText(res2)

        }

        if(check==true)
        {
            result.setText(res2)
        }

    }

    fun seperation(s1:String)
    {
        try {
            val part = calc.split(" ")
            num1 = part[0].toFloat()
            operator = part[1].toString()
            num2 = part[2].toFloat()
        }
        catch (e: NumberFormatException) {
            println("Invalid number format")
        }
    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculation = findViewById(R.id.calculation)
        result = findViewById(R.id.result)
        btn_ac = findViewById(R.id.AC)
        btn_backspace = findViewById(R.id.BACKSPACE)
        btn0 = findViewById(R.id.Zero)
        btn1 = findViewById(R.id.One)
        btn2 = findViewById(R.id.Two)
        btn3 = findViewById(R.id.Three)
        btn4 = findViewById(R.id.Four)
        btn5 = findViewById(R.id.Five)
        btn6 = findViewById(R.id.Six)
        btn7 = findViewById(R.id.Seven)
        btn8 = findViewById(R.id.Eight)
        btn9 = findViewById(R.id.Nine)
        decimal = findViewById(R.id.Decimal)
        divide = findViewById(R.id.Divide)
        multiply = findViewById(R.id.Multiply)
        subtract = findViewById(R.id.Subtract)
        add = findViewById(R.id.Plus)
        equalsto = findViewById(R.id.Equalsto)


        // Code to execute when the buttons are clicked

        btn0.setOnClickListener {
            calc += "0"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn1.setOnClickListener {

            calc += "1"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn2.setOnClickListener {
            calc += "2"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn3.setOnClickListener {
            calc += "3"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn4.setOnClickListener {
            calc += "4"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn5.setOnClickListener {
            calc += "5"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn6.setOnClickListener {
            calc += "6"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn7.setOnClickListener {
            calc += "7"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn8.setOnClickListener {
            calc += "8"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn9.setOnClickListener {
            calc += "9"
            calculation_disp(calc)
            button_state1=true
            check=true
        }

        btn_backspace.setOnClickListener {
            if (calc.length != null) {
                calc = calc.dropLast(1)
                calculation_disp(calc)
                button_state1=true
                check=true
            }

        }

        decimal.setOnClickListener {
            if (button_state1 == true) {
                if (calc.isEmpty()) {
                    calc += "0."
                    calculation_disp(calc)
                } else if (calc.isNotEmpty()) {
                    calc += "."
                    calculation_disp(calc)
                }
                button_state1=false
            }
        }

        add.setOnClickListener {
            if (button_state1 == true) {
                calc += "+"
                operator = "+"
                calculation_disp(calc)
                button_state1=false
            }
        }

        subtract.setOnClickListener {
            if (button_state1 == true) {
                calc += "-"
                operator = "-"
                calculation_disp(calc)
                button_state1=false
            }
        }

        multiply.setOnClickListener {
            if (button_state1 == true) {
                calc += "*"
                operator = "*"
                calculation_disp(calc)
                button_state1=false
            }
        }

        divide.setOnClickListener {
            if (button_state1==true)
            calc += "/"
            operator="/"
            calculation_disp(calc)
            button_state1=false
        }

        btn_ac.setOnClickListener {
            calc = ""
            res = ""
            calculation_disp(calc)
            result_disp(res)
            button_state1=true
            check=true
        }

        fun evaluateExpression(expression: String): Double {
            return try {
                val exp = ExpressionBuilder(expression).build()
                exp.evaluate()
            } catch (e: Exception) {
                println("Error: ${e.message}")
                0.0 // Return 0.0 or handle the error condition accordingly
            }
        }

        equalsto.setOnClickListener{


            when(operator) {
                "+" -> {
                    val result = evaluateExpression(calc)
                    res=result.toString()
                    result_disp(res)
                }

                "-" -> {
                    val result = evaluateExpression(calc)
                    res=result.toString()
                    result_disp(res)
                }

                "*" -> {
                    val result = evaluateExpression(calc)
                    res=result.toString()
                    result_disp(res)
                }

                "/" -> {
                    val result = evaluateExpression(calc)
                    res=result.toString()
                    result_disp(res)
                }
            }

        }

    }



}

