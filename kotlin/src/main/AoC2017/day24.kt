package main.AoC2017

import java.io.File
import java.util.*


fun main(args: Array<String>){
    
        val nodes = File("inputs/2017/day24.in").readLines()
                .map { l ->
                    val split = l.split("/")
                    Pair(split.first().toInt(), split.last().toInt())
                }

        val bridges: MutableSet<List<Pair<Int, Int>>> = mutableSetOf()
        val possibleBridges: Deque<List<Pair<Int, Int>>> = LinkedList()

        nodes.filter { it.first == 0 || it.second == 0 }
                .forEach {
                    val start = if (it.first == 0) it else Pair(it.second, it.first)
                    possibleBridges.add(listOf(start))
                }

        while (possibleBridges.size != 0) {
            val currentBridge = possibleBridges.removeFirst()
            val exposed = currentBridge.last().second
            bridges.add(currentBridge)
            nodes.filter {
                (!currentBridge.contains(it)
                        && !currentBridge.contains(Pair(it.second, it.first))) && (it.first == exposed || it.second == exposed)
            }
                    .map { if (it.first == exposed) it else Pair(it.second, it.first) }
                    .forEach { possibleBridges.addLast(currentBridge + listOf(it)) }
        }

        println("Part 1: ${bridges.map { it.sumBy { it.first + it.second } }.max()}")
        println("Part 2: ${bridges.sortedBy { it.size }.last().sumBy { it.first + it.second }}")
}