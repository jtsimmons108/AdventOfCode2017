package main

import java.io.File
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main(args: Array<String>){

        val moves = File("inputs/day11.in").readText().split(",")
        var point = Pair(0,0)
        var maxDistance = 0
        for(move in moves){
            point = moveInGrid(point, move)
            maxDistance = maxOf(maxDistance, getDistanceBetweenPoints(point, Pair(0,0)))
        }
        println("Part 1: ${getDistanceBetweenPoints(point, Pair(0,0))}")
        println("Part 2: ${maxDistance}")

}


fun moveInGrid(point: Pair<Int, Int>, direction: String): Pair<Int, Int>{
    val (col, row) = point
    return when(direction){
        "n" -> Pair(col, row - 1)
        "s" -> Pair(col, row + 1)
        "sw" -> Pair(col - 1, row + 1)
        "se" -> Pair(col + 1, row)
        "nw" -> Pair(col - 1, row)
        "ne" -> Pair(col + 1, row - 1)
        else -> point
    }
}

fun convertAxialToCube(point: Pair<Int, Int>): Triple<Int, Int, Int>{
    val (col, row) = point
    val x = col
    val z = row
    val y = -x - z
    return Triple(x, y, z)
}

fun getDistanceBetweenPoints(point1: Pair<Int, Int>, point2: Pair<Int, Int>):Int{
    val (x1, y1, z1) = convertAxialToCube(point1)
    val (x2, y2, z2) = convertAxialToCube(point2)
    return (abs(x1 - x2) + abs(y1 - y2) + abs(z1 - z2)) / 2
}

