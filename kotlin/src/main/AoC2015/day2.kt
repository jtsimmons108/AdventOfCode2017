package main.AoC2015

import java.io.File

fun main(args: Array<String>){

    val input = File("inputs/2015/day2.in").readLines()
            .map { it.split("x").map { it.toInt() }.sorted() }


    val paper = input.sumBy { 3*it[0]*it[1] + 2*it[1]*it[2] + 2*it[0]*it[2] }
    val ribbon = input.sumBy { 2 * (it[0] + it[1]) + it[0]*it[1]*it[2] }

    println("Part1: $paper")
    println("Part2: $ribbon")


}