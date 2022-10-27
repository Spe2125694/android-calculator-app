package com.example.android_calculator_app

import android.icu.util.IslamicCalendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvCalculatorScreen: TextView?= null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val one: Button = findViewById(R.id.btn1)
//        val two: Button = findViewById(R.id.btn2)
//        val three: Button = findViewById(R.id.btn3)
//        val four: Button = findViewById(R.id.btn4)
//        val five: Button = findViewById(R.id.btn5)
//        val six: Button = findViewById(R.id.btn6)
//        val seven: Button = findViewById(R.id.btn7)
//        val eight: Button = findViewById(R.id.btn8)
//        val nine: Button = findViewById(R.id.btn9)
//        val zero: Button = findViewById(R.id.btn0)
//
//        val divide: Button = findViewById(R.id.btnDiv)
//        val multiply: Button = findViewById(R.id.btnMultiply)
//        val add: Button = findViewById(R.id.btnAdd)
//        val subtract: Button = findViewById(R.id.btnSubtract)
//        val clear: Button = findViewById(R.id.btnClr)
//        val equals: Button = findViewById(R.id.btnEquals)

        tvCalculatorScreen = findViewById(R.id.calcScreen)
    }

    fun onDigit(view: View) {
        tvCalculatorScreen?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View) {
        tvCalculatorScreen?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot) {
            tvCalculatorScreen?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        tvCalculatorScreen?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvCalculatorScreen?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
    }
}

    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvCalculatorScreen?.text.toString()
            var prefix = "-"

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvCalculatorScreen?.text = (one.toDouble() - two.toDouble()).toString()
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

}