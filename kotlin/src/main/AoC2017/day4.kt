package main.AoC2017

import java.io.File

fun main(args: Array<String>){

    val input = File("inputs/2017/day4.in").readLines()
            .map { it.split(" ") }

    val valid = input.count { it.size == it.toSet().size }

    val validAnagrams = input.map { it.map { it.toCharArray().sorted().joinToString("")} }
            .count { it.size == it.toSet().size }

    println("Part 1: $valid")
    println("Part 2: $validAnagrams")

}