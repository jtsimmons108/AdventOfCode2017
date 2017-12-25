package main.AoC2017

import java.io.File
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {
    
    println(measureTimeMillis {
        var sum1: Int = 0
        var sum2: Int = 0
        val input = File("inputs/2017/day1.in").readText()
        for (i in input.indices) {

            //Part 1 - Check if it is the same as the one next to it
            if (input[i] == input[(i + 1) % input.length]) {
                sum1 += input[i] - '0'
            }

            //Part 2 - Check if it is the same as the one halfway across from it
            if (input[i] == input[(i + input.length / 2) % input.length]) {
                sum2 += input[i] - '0'
            }
        }
        println("Part1: $sum1")
        println("Part2: $sum2")
    })
}
