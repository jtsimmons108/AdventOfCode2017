package main.AoC2017

import java.io.File

val registers: MutableMap<String, Int> = HashMap<String, Int>().withDefault { _ -> 0 }

fun main(args: Array<String>){



    val input = File("inputs/2017/day8.in").readLines()
    var maxValue = Integer.MIN_VALUE
    input.map {

        val values = it.split(" ")
        if(checkCondition(values[4], values[5], values[6])) {
            var toAdd = values[2].toInt()
            if (values[1] == "dec")
                toAdd *= -1
            registers[values[0]] = registers.getValue(values[0]) + toAdd
        }

        if(!registers.values.isEmpty())
            maxValue = maxOf(maxValue, registers.values.max()!!)

    }

    println("Part 1: ${registers.values.max()}")
    println("Part 2: $maxValue")

}

fun checkCondition(reg: String, op: String, value: String) : Boolean {
    val left = registers.getValue(reg)
    val right = value.toInt()
    return when (op) {
        "==" -> left == right
        "!=" -> left != right
        "<=" -> left <= right
        ">=" -> left >= right
        "<" -> left < right
        ">" -> left > right
        else -> false

    }
}