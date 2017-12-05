package main

import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val input = File("inputs/day5.in").readLines()
            .map { it.toInt() }

    for (part in 1..2) {
        val instructions = input.toMutableList()
        var (i, steps) = Pair(0, 0)

        while (i >= 0 && i < instructions.size) {
            val off = instructions[i]
            when (part) {
                1 -> instructions[i]++
                2 -> if (instructions[i] >= 3) instructions[i]-- else instructions[i]++
            }
            i += off
            steps++
        }
        println("Part $part: $steps")
    }


}