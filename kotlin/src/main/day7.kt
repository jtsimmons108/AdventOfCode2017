package main

import sun.jvm.hotspot.oops.CellTypeState.top
import java.io.File
import java.util.regex.Pattern

val weights = HashMap<String, Int>()
val nodes = HashMap<String, List<String>>()

fun main(args: Array<String>) {

    val input = File("inputs/day7.in").readLines()
    val pattern = Regex("(\\w+)\\s\\((\\d+)\\)(?: -> )?([\\w\\,\\s]*)")

    input.map {
        val matches = pattern.find(it)?.groupValues!!
        nodes[matches[1]] = matches[3].split(", ")
        weights[matches[1]] = matches[2].toInt()
    }
    val top = nodes.keys.filter { node ->
        nodes.values.count{it.contains(node)} == 0
    }.first()
    println(top)
}