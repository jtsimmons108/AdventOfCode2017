package main.AoC2017

import java.io.File
import kotlin.system.measureTimeMillis


fun main(args: Array<String>){
    
    val input = File("inputs/2017/day22.in").readLines()
    println("Part 1: ${solvePart1(input)}")
    println("Part 2: ${solvePart2(input)}")
}


fun solvePart1(input: List<String>): Int{
    val points = mutableMapOf<Pair<Int, Int>, Int>().withDefault { 0 }
    input.mapIndexed{r, line ->
                line.mapIndexed{
                    c, char -> points[Pair(r, c)] = if (char == '#') 1 else 0
                }
    }
    var (r, c) = Pair(input.size/2,input[0].length/2)
    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    var infected = 0
    var direction = 0
    repeat(10000){
        val currentNode = points.getValue(Pair(r, c))
        if(currentNode == 0){
            direction = (direction + 3)  % 4
            infected++
        }else{
            direction = (direction + 1) % 4
        }
        points[Pair(r, c)] = (currentNode + 1) % 2
        val (dr, dc) = directions[direction]
        r += dr
        c += dc
    }
    return infected
}

fun solvePart2(input: List<String>): Int{
    val points = mutableMapOf<Pair<Int, Int>, Int>().withDefault { 0 }
    input.mapIndexed{r, line ->
        line.mapIndexed{
            c, char -> points[Pair(r, c)] = if (char == '#') 2 else 0
        }
    }
    var (r, c) = Pair(input.size/2,input[0].length/2)
    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    var infected = 0
    var direction = 0
    repeat(10000000){
        val currentNode = points.getValue(Pair(r, c))
        if(currentNode == 1) infected++
        direction = when(currentNode){
            0 -> (direction + 3) % 4
            1 ->  direction
            2 -> (direction + 1) % 4
            3 -> (direction + 2) % 4
            else -> throw IllegalArgumentException()
        }
        
        
        points[Pair(r, c)] = (currentNode + 1) % 4
        val (dr, dc) = directions[direction]
        r += dr
        c += dc
    }
    return infected
}

