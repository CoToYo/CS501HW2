package com.example.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

//import kotlinx.and

class MainActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener{

    private lateinit var firstNum: EditText
    private lateinit var secNum: EditText
    private lateinit var output: TextView
    private var num1: Float? = 0.0f
    private var num2: Float? = 0.0f
    private lateinit var go: Button
    private lateinit var map:ArrayAdapter<List<String>>
    private var TAG:String = "unreal"
    private var operation: Int =1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        output = findViewById(R.id.out)

//        var z : String = "AA bb".split(" ")[0]

        firstNum = findViewById(R.id.first_num)
        secNum = findViewById(R.id.second_sum)
        go = findViewById(R.id.crime_date)

//        val n1
//        val n2
        go.setOnClickListener{
            if(num1 == null || num2 == null){
                println("Please give some valid input")
                output.apply{text = "403 bad request"}
//                val n1 = num1!!
//                val n2 = num2!!
            }else{

                val ret = when(operation) {
                    0 -> num1!!+num2!!
                    1 -> num1!!-num2!!
                    2 -> num1!!*num2!!
                    3 -> num1!!/num2!!
                    4->  num1!!%num2!!
                    else -> {"403 Bad request"}
                }

                output.apply{text = ret.toString()}


                println(ret)

            }
        }

        val spinner: Spinner = findViewById(R.id.planets_spinner)
        spinner.onItemSelectedListener = this
        val zz = ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        )
//        val kk:List<String> = listOf(zz)
        println("--------------------------  .get[]"+zz.getItem(2))
        println("aaaa")
        println()
//        for( full in zz){
//
//        }
// Create an ArrayAdapter using the string array and a default spinner layout
        val z = ArrayAdapter.createFromResource(
            this,
            R.array.sign_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

        }

        val titleWatcher = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var z = 1;
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                num1 = p0.toString().toFloatOrNull()
                println(num1)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }

        val numWatcher = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var z = 1;
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                num2 = p0.toString().toFloatOrNull()
                println(num2)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }


        firstNum.addTextChangedListener(titleWatcher)
        secNum.addTextChangedListener(numWatcher)


//        findViewById<View>()

//        pie.set



    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        println(p0)
//        println(p0)
//        println(p0)
//        println(p0)

        println("------------------- ------------------------------")

        val zz = ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        )

        println(zz.getItem(p2))
        go.apply { text = zz.getItem(p2) }
        operation = p2

        Log.d(TAG, "onItemSelected: $zz")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}