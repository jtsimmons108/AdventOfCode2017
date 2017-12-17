package main.AoC2017

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val nums = mutableListOf(0)
    var position = 0
    for (i in 1..2017){
        position = (position + 349) % i + 1
        nums.add(position, i)
    }

    var afterZero = 0
    position = 0
    for (i in 1..50000000) {
        position = (position + 349) % i + 1
        if (position == 1) {
            afterZero = i
        }

    }

    println("Part 1: ${nums[nums.indexOf(2017) + 1]}")
    println("Part 2: $afterZero")


}