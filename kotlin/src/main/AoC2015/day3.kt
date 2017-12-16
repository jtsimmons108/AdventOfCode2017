package main.AoC2015

import java.io.File
import javax.swing.text.html.HTML.Tag.P

fun main(args: Array<String>){

    val input = File("inputs/2015/day3.in").readText();
    val locations = mutableListOf(Pair(0,0))
    val dualLocations = listOf(mutableListOf(Pair(0,0)), mutableListOf(Pair(0,0)))

    input.map {
        val (x, y) = locations.last()
        when(it){
            '<' -> locations.add(Pair(x - 1, y))
            '>' -> locations.add(Pair(x + 1, y))
            '^' -> locations.add(Pair(x, y + 1))
            'v' -> locations.add(Pair(x, y - 1))
            else -> throw IllegalArgumentException()
        }
    }

    input.mapIndexed { i, c ->
        val currentList = dualLocations[i % 2]
        val (x, y) =currentList.last()
        when(c){
            '<' -> currentList.add(Pair(x - 1, y))
            '>' -> currentList.add(Pair(x + 1, y))
            '^' -> currentList.add(Pair(x, y + 1))
            'v' -> currentList.add(Pair(x, y - 1))
            else -> throw IllegalArgumentException()
        }
    }
    val soloVisits = locations.toSet().size
    val dualVisits = (dualLocations[0] + dualLocations[1]).toSet().size

    println("Part 1: $soloVisits")
    println("Part 2: $dualVisits")

}