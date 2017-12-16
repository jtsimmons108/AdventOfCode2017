package main.AoC2017

import java.io.File


fun main(args: Array<String>) {

    val input = File("inputs/2017/day13.in").readLines()
    val values = input.map { it.split(": ").map { it.toInt() } }
            .associate { it.first() to it.last() }

    val severity = values.entries
            .map { if (it.key % (2 * (it.value - 1)) == 0) it.key * it.value else 0 }
            .sum()

    var delay = 0
    while (values.entries.any { (it.key + delay) % (2 * (it.value - 1)) == 0 })
        delay++

    println("Part 1: $severity")
    println("Part 2: $delay")

}