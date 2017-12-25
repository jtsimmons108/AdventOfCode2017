package main.AoC2017

import kotlin.math.abs
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {

    
    val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    val points = mutableListOf(Pair(0, 0))
    val values = hashMapOf(Pair(0, 0) to 1)
    val target = 361527
    var direction = 0
    var stepLength = 1

    while (points.size < target) {
        for (times in 0 until 2) {
            for (steps in 0 until stepLength) {
                var (x, y) = points[points.lastIndex]
                var (dx, dy) = directions[direction]
                val currentPoint = Pair(x + dx, y + dy)
                points.add(currentPoint)
                val value = getAllNeighbors(currentPoint)
                        .map { values[it] }
                        .filterNotNull()
                        .sum()
                values[currentPoint] = value
            }
            direction = (direction + 1) % 4
        }
        stepLength++
    }

    println("Part 1: ${points[target - 1].toList().map { abs(it) }.sum()}")
    println("Part 2: ${values.values.filter { it > target }.sorted().first()}")

}

fun getAllNeighbors(point: Pair<Int, Int>): List<Pair<Int, Int>> {
    val neighbors = mutableListOf<Pair<Int, Int>>()
    val (x, y) = point
    for (dx in -1..1)
        for (dy in -1..1)
            if (dx != 0 || dy != 0)
                neighbors.add(Pair(x + dx, y + dy))
    return neighbors
}