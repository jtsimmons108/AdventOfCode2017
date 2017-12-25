package main.AoC2017

import java.io.File

fun main(args: Array<String>) {
    
    val nodes = File("inputs/2017/day24.in").readLines()
            .map { l ->
                val split = l.split("/")
                Pair(split.first().toInt(), split.last().toInt())
            }
    val validBridges = mutableSetOf<List<Pair<Int, Int>>>()
    nodes.filter { it.first == 0 || it.second == 0 }
            .forEach {
                val start = if (it.first == 0) it else Pair(it.second, it.first)
                addBridgeLink(listOf(start), nodes - it, validBridges)
            }
    println(validBridges.map { it.sumBy { it.first + it.second } }.max())
    println(validBridges.sortedBy { it.size }.last().sumBy { it.first + it.second })
}

fun addBridgeLink(links: List<Pair<Int, Int>>, remaining: List<Pair<Int, Int>>,
                  validBridges: MutableSet<List<Pair<Int, Int>>>) {
    validBridges.add(links)
    val exposed = links.last().second
    remaining.filter { it.first == exposed || it.second == exposed }
            .forEach {
                val next = if (it.first == exposed) it else Pair(it.second, it.first)
                addBridgeLink(links + next, remaining - it, validBridges)
            }
}