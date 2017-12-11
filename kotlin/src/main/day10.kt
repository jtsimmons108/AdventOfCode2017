package main

import java.io.File
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main(args: Array<String>){

    val input = File("inputs/day10.in").readText()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")

}

fun part1(input: String) : Int{
    val moves = input.split(",").map { it.toInt() }
    var data = IntRange(0, 255).toList()
    var start = 0
    var skip = 0

    for(move in moves){
        data = twist(data, start, move)
        start = (start + move + skip++) % data.size
    }
    return data[0] * data[1]
}

fun part2(input: String): String{
    val moves = input.map{it.toInt()}.toList() + listOf(17, 31, 73, 47, 23)
    var data = IntRange(0, 255).toList()
    var start = 0
    var skip = 0

    for(i in 0 until 64) {
        for (move in moves) {
            data = twist(data, start, move)
            start = (start + move + skip++) % data.size
        }
    }
    
    return data.chunked(16)
            .map{it.reduce{x, y -> x xor y}}
            .map{"%02x".format(it)}
            .joinToString("")
}


fun twist(data: List<Int>, start: Int, offset: Int ) : List<Int>{
    val end = start + offset
    val toReverse = if(end > data.size){
        (data.subList(start, data.size) + data.subList(0, end % data.size)).toMutableList()
    }else {
        data.subList(start, end).toMutableList()
    }
    val newList = data.toMutableList()
    for(i in toReverse.indices){
        newList[(start + i) % newList.size] = toReverse[toReverse.size - 1 - i]
    }
    return newList.toList()
}


