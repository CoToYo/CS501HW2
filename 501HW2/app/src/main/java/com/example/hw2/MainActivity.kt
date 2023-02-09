package com.example.hw2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var str = "0"
    private var processStr = "0"

    private var canDot = true

    private var useOperational = false


    private lateinit var inputExpression: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        et_input.setOnClickListener{
//
//        }

        inputExpression = findViewById(R.id.et_input)


        btn_clear.setOnClickListener {
            str = "0"
            processStr = "0"
            canDot = true
            useOperational = false
            et_input.setText(str)
        }

        btn_0.setOnClickListener {
            if(str != "0"){
                str += "0"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_1.setOnClickListener {
            if(str == "0"){
                str = "1"
            } else {
                str += "1"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_2.setOnClickListener {
            if(str == "0"){
                str = "2"
            } else {
                str += "2"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_3.setOnClickListener {
            if(str == "0"){
                str = "3"
            } else {
                str += "3"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_4.setOnClickListener {
            if(str == "0"){
                str = "4"
            } else {
                str += "4"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_5.setOnClickListener {
            if(str == "0"){
                str = "5"
            } else {
                str += "5"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_6.setOnClickListener {
            if(str == "0"){
                str = "6"
            } else {
                str += "6"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_7.setOnClickListener {
            if(str == "0"){
                str = "7"
            } else {
                str += "7"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_8.setOnClickListener {
            if(str == "0"){
                str = "8"
            } else {
                str += "8"
            }
            useOperational = false
            et_input.setText(str)
        }
        btn_9.setOnClickListener {
            if(str == "0"){
                str = "9"
            } else {
                str += "9"
            }
            useOperational = false
            et_input.setText(str)
        }


        btn_dot.setOnClickListener {
            if (canDot && !useOperational){
                str += "."
                canDot = false
                et_input.setText(str)
            }
        }

        // calculating rules (+-*/)
        btn_plus.setOnClickListener {
            // 判断最近有没有用过运算符
            if (str.length >= 3 && (str[str.length - 2] == '+' || str[str.length - 2] == '-' ||
                        str[str.length - 2] == '*' || str[str.length - 2] == '/')){
                str = str.substring(0, str.length - 3)
                useOperational = true
            }
            if (str.last() == '.')
                str = str.substring(0, str.length - 1)
            str += " + "
            canDot = true
            et_input.setText(str)
        }
        btn_minus.setOnClickListener {

            if (str.length >= 3 && (str[str.length - 2] == '+' || str[str.length - 2] == '-' ||
                        str[str.length - 2] == '*' || str[str.length - 2] == '/')){
                str = str.substring(0, str.length - 3)
                useOperational = true
            }
            if (str.last() == '.')
                str = str.substring(0, str.length - 1)
            str += " - "
            canDot = true
            et_input.setText(str)
        }
        btn_multipy.setOnClickListener {

            if (str.length >= 3 && (str[str.length - 2] == '+' || str[str.length - 2] == '-' ||
                        str[str.length - 2] == '*' || str[str.length - 2] == '/')){
                str = str.substring(0, str.length - 3)
                useOperational = true
            }
            if (str.last() == '.')
                str = str.substring(0, str.length - 1)
            str += " * "
            canDot = true
            et_input.setText(str)
        }
        btn_divide.setOnClickListener {

            if (str.length >= 3 && (str[str.length - 2] == '+' || str[str.length - 2] == '-' ||
                        str[str.length - 2] == '*' || str[str.length - 2] == '/')){
                str = str.substring(0, str.length - 3)
                useOperational = true
            }
            if (str.last() == '.')
                str = str.substring(0, str.length - 1)
            str += " / "
            canDot = true
            et_input.setText(str)
        }



        btn_del.setOnClickListener {

            if (str.last() == ' '){
                str = str.substring(0, str.length - 1)
            }
            str = if (str.length >= 2)
                str.substring(0, str.length - 1)
            else
                "0"

            if (str.last() == ' ' && str[str.length - 2] != '+'  && str[str.length - 2] != '-'
                && str[str.length - 2] != '*' && str[str.length - 2] != '/'){
                str = str.substring(0, str.length - 1)
            }

            if (str.length == 1) {
                if (str == "-"){
                    str = "0"
                }
                canDot = true
                useOperational = false
            }
            et_input.setText(str)
        }


        btn_result.setOnClickListener {

            if (useOperational){
                str = str.substring(0, str.length - 3)
                useOperational = false
            }
            if (str.last() == '.')
                str = str.substring(0, str.length - 1)

            try{
                var result = Calculator.cal("$str + 0")
                processStr = str
                str = result
                et_input.setText(str)
                canDot = true
                useOperational = false
            } catch (e: MyException) {
                Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show()
            }
        }


        btn_process.setOnClickListener {
            if (processStr != "0") {
                str = processStr
                processStr = "0"
                et_input.setText(str)
            }
        }
    }
}
