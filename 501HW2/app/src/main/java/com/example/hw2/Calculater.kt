package com.example.hw2

import java.lang.Math.pow
import kotlin.math.sqrt



class Calculator {
    companion object {
        //
        fun cal(s: String): String {
            var str = s
            var n1: Double
            var n2: Double
            var tn = 0.0
            var lastPriority = 0
            var priority: Int
            var op: Char
            var numS = ArrayList<Double>()
            var opS = ArrayList<Char>()
            var result: Double = 0.0
            var tempNumStr: String = ""
            var i: Int = 0

            try{

                while (i < s.length) {

                    if (str[i] in '0'..'9') {
                        tempNumStr = ""
                        while (i < s.length && (str[i] in '0'..'9' || str[i] == '.')) {
                            tempNumStr += str[i]
                            i++
                        }
                        tn = string2float(tempNumStr)
                    } else if (str[i] == ' ') {
                        i++
                        continue
                    } else {
                        numS.add(tn)
                        priority = getPriority(str[i])
                        if (priority <= lastPriority) {
                            while (opS.size != 0) {
                                op = opS.last()
                                lastPriority = getPriority(op)

                                if (lastPriority >= priority) {
                                    opS.removeAt(opS.size - 1)
                                    n2 = numS.last()
                                    numS.removeAt(numS.size - 1)
                                    n1 = numS.last()
                                    numS.removeAt(numS.size - 1)
                                    n1 = calOne(n1, op, n2)
                                    numS.add(n1)
                                } else {
                                    break
                                }
                            }
                            opS.add(str[i])
                        } else {
                            opS.add(str[i])
                        }
                        tn = 0.0
                        lastPriority = priority
                    }
                    i++
                }

                numS.add(tn);

                while (opS.size != 0){
                    op = opS.last()
                    opS.removeAt(opS.size - 1)
                    n2 = numS.last()
                    numS.removeAt(numS.size - 1)
                    n1 = numS.last()
                    numS.removeAt(numS.size - 1)
                    result = calOne(n1, op, n2)
                    numS.add(result)
                }}catch(e: MyException){
                throw MyException("Cannot divide by 0!!! from cal")
            }
            return beautify(result.toString())
        }

        private fun getPriority(op: Char): Int {
            //
            var priority: Int = -1
            if (op == 'k')
                priority = 1
            else if (op == '+' || op == '-')
                priority = 2
            else if (op == '*' || op == '/')
                priority = 3
            else if (op == '^')
                priority = 4
            return priority
        }

        private fun calOne(n1: Double, op: Char, n2: Double): Double {
            var result: Double = 0.0
            if(op == '+'){
                result = n1 + n2
            } else if (op == '-') {
                result = n1 - n2
            } else if (op == '*') {
                result = n1 * n2
            } else if (op == '/') {
                if (n2 > -0.000001 && n2 < 0.000001)
                    throw MyException("Cannot divided by 0!!! from cal_one")
                result = n1 / n2
            } else if (op == 'k') {
                result = Math.sqrt(n1)
            }
            return result
        }

        private fun beautify(s: String): String{
            // 去掉小数点后多于的 0
            var str = s
            var flag: Boolean = true  // 是否可以计算小数点后0的个数
            var zeroNum: Int = 0
            for (i in str.length - 1 downTo 0){
                if (str[i] == '.') {
                    str = str.substring(0, str.length - zeroNum)
                    break
                }
                if (str[i] >= '1' && str[i] <= '9'){
                    flag = false
                }
                if (str[i] == '0' && flag) {
                    zeroNum++
                }
            }
            // Remove the decimal point of a number with input like 1.0
            if (str.last() == '.')
                str = str.substring(0, str.length - 1)
            return str
        }

        private fun string2float(s: String): Double
        {
            var n: Double = 0.0
            var isXiaoShu: Boolean = false // Determine if the current number is a decimal part
            var xiaoShuLength: Int = 0;  // Record the length of the decimal part
            for (i in 0 until s.length){
                if (s[i] in '0'..'9'){
                    n = n * 10 + (s[i] - '0').toDouble()
                    if (isXiaoShu)
                        xiaoShuLength++
                }
                else if (s[i] == '.'){
                    isXiaoShu = true
                }
            }
            return n * 1.0 / pow(10.0, xiaoShuLength.toDouble())
        }
    }
}

public class MyException(override val message: String) : Throwable(){

}
