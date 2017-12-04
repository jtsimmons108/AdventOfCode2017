package main

import java.io.File

fun main(args: Array<String>){

    val input = File("inputs/day4.in").readLines()
            .map { it.split(" ") }

    val valid = input.count { it.size == it.toSet().size }

    val validAnagrams = input.map { it.map { String(it.toCharArray().sortedArray())} }
            .count { it.size == it.toSet().size }

    println("Part 1: $valid")
    println("Part 2: $validAnagrams")
    
}