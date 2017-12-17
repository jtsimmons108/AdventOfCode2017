package main.AoC2017

import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val input = File("inputs/2017/day16.in").readText().split(",")
    var data = "abcdefghijklmnop"
    val seen = mutableListOf<String>()

    while (!seen.contains(data)) {
        seen.add(data)
        input.map { data = dance(data, it) }
    }
    println("Part 1: ${seen[1]}")
    println("Part 2: ${seen[1000000000 % seen.size]}")

}


fun dance(data: String, instruction: String): String {
    val move = instruction[0]
    val inputs = instruction.substring(1).split("/")

    if (move == 's') {
        val amt = inputs.first().toInt()
        return data.drop(data.length - amt) + data.take(data.length - amt)
    } else if (move == 'x') {
        val (indexA, indexB) = inputs.map { it.toInt() }
        return data.replace(data[indexA], 'x').replace(data[indexB], data[indexA]).replace('x', data[indexB])
    } else {
        val (charA, charB) = inputs
        return data.replace(charA, "x").replace(charB, charA).replace("x", charB)
    }


}