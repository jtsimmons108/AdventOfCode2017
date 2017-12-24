package main.AoC2017

import java.io.File
import java.lang.Character.getNumericValue
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis


fun main(args: Array<String>){

    val input = File("inputs/2017/day23.in").readLines()
    println("Part 1: ${Day23.solvePart1(input)}")
    println("Part 1: ${Day23.solvePart2()}")

}

object Day23 {
    
    fun solvePart1(instructions: List<String>): Int {
        val registers = "abcdefgh".associate { it.toString() to 0 }.toMutableMap()
        var (mulCalls, i) = Pair(0, 0)

        while (i < instructions.size) {
            val (op, reg, value) = instructions[i].split(" ")
            if (op == "jnz") {
                if (getNumericValue(reg, registers) != 0) {
                    i += getNumericValue(value, registers)
                } else {
                    i++
                }
            } else {
                when (op) {
                    "set" -> registers[reg] = getNumericValue(value, registers)
                    "sub" -> registers[reg] = registers.getValue(reg) - getNumericValue(value, registers)
                    "mul" -> {
                        registers[reg] = registers.getValue(reg) * getNumericValue(value, registers); mulCalls++
                    }
                }
                i++
            }

        }

        return mulCalls
    }

    fun solvePart2(): Int{
        var b = 81 * 100 + 100000
        var c = b + 17000
        var h = 0
        
        (b..c step 17).forEach{ n ->
            for(i in 2..sqrt(n.toDouble()).toInt()){
                if(n % i == 0){
                    h++
                    break
                }
            }
        }
        return h
    }
    
    fun getNumericValue(reg: String, registers: Map<String, Int>): Int {
        return if (reg in registers) registers.getValue(reg) else reg.toInt()
    }
}
