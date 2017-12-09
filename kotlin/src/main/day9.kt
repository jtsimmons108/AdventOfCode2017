package main

import java.io.File

var garbage = Regex("<[^>]*>")

fun main(args: Array<String>) {

    val s = File("inputs/day9.in").readText().replace(Regex("!."), "")
    val part2 = getGarbageCount(s)
    val part1 = getScore(s.replace(garbage, ""))

    println("Part 1: $part1")
    println("Part 2: $part2")


}

fun getGarbageCount(stream: String): Int {
    return garbage.findAll(stream).toList()
            .map {
                it.value.length - 2
            }.sum()
}

fun getScore(stream: String): Int {
    var score = 0
    var level = 0
    stream.forEach {
        if (it == '{') {
            level++
        } else if (it == '}') {
            score += level--
        }

    }
    return score
}
