package main

import com.sun.deploy.util.OrderedHashSet


fun main(args: Array<String>){


    val input = "10\t3\t15\t10\t5\t15\t5\t15\t9\t2\t5\t8\t5\t2\t3\t6"
            .split("\t")
            .map { it.toInt() }
            .toMutableList()

    var total = 0
    val length = input.size
    val seen = mutableListOf<List<Int>>()

    while(seen.indexOf(input.toList()) == -1){

        seen.add(input.toList())
        var toDistribute = input.max()!!
        val maxPosition = input.indexOf(toDistribute)
        input[maxPosition] = 0
        var index = (maxPosition + 1) % length

        while(toDistribute > 0){
            toDistribute--
            input[index]++
            index = (index + 1) % length

        }
        total++
    }

    println("Part 1: $total")
    println("Part 2: ${total - seen.indexOf(input.toList())}")

}