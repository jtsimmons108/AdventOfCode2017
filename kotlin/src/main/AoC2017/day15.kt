package main.AoC2017

import java.io.File

fun main(args: Array<String>){

    val input = File("inputs/2017/day15.in").readText().split(",")
    var data = "abcdefghijklmnop"

    input.map { data = dance(data, it) }

    println("Part 1: $data")

    var i = 1
    while(data != "abcdefghijklmnop"){
        input.map { data = dance(data, it) }
        i++
    }

    for(i in 0 until 1000000000 % i){
        input.map { data = dance(data, it) }
    }

    println("Part 2: $data")
}


fun dance(data: String, instruction: String): String{
    val move = instruction[0]
    val inputs = instruction.substring(1).split("/")

    if(move == 's'){
        val amt = inputs.first().toInt()
        return data.drop(data.length - amt) + data.take(data.length - amt)
    }else if(move == 'x'){
        val (indexA, indexB) = inputs.map{it.toInt()}
        return data.replace(data[indexA], 'x').replace(data[indexB], data[indexA]).replace('x', data[indexB])
    }else{
        val (charA, charB) = inputs
        return data.replace(charA, "x").replace(charB, charA).replace("x", charB)
    }


}