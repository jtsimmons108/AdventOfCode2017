package main.AoC2015

import java.io.File


fun main(args: Array<String>) {

    val input = File("inputs/2015/day1.in").readText()
    val floor = input.sumBy { if(it == '(') 1 else -1 }

    var total = 0
    var index = 0
    while(total > -1){
        total += if(input[index++] == '(') 1 else -1
    }

    println("Part 1: $floor")
    println("Part 2: ${index}")

}