package main.AoC2017

import java.io.File

fun main(args: Array<String>){

    val input = File("inputs/2017/day12.in").readLines()
    val connectedNodes = mutableMapOf<Int, List<Int>>()
    val branches = mutableSetOf<MutableSet<Int>>()
    var total = 0

    input.map {
        val info = it.split(" <-> ")
        connectedNodes[info[0].toInt()] = info[1].split(", ").map{it.toInt()}.toList()
    }

    connectedNodes.keys.map {
        val connected = mutableSetOf<Int>()
        val toCheck = connectedNodes.getValue(it).toMutableList()
        while(toCheck.size > 0){
            val current = toCheck.removeAt(0)
            if(!connected.contains(current)){
                connected.add(current)
                toCheck.addAll(connectedNodes.getValue(current))
            }
        }
        if(connected.contains(0))
            total++
        branches.add(connected)
    }

    println("Part 1: $total")
    println("Part 2: ${branches.size}")
}