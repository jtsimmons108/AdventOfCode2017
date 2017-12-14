package main

import java.io.File


fun main(args: Array<String>){

    val grid = mutableListOf<MutableList<Int>>()


    for(num in 0..127){
        grid.add(getBinaryString(getKnotHash("jxqlasbh-$num"))
                .map { it.toInt() - '0'.toInt() }.toMutableList())
    }
    val total = grid.map { it.count { i -> i == 1 } }.sum()
    println("Part 1: $total")

    println(grid)

}


fun getBinaryString(input: String): String{
    return input.map { Integer.parseInt(it.toString(), 16) }.map{it.toString(2).padStart(4, '0')}.joinToString("");
}

fun getKnotHash(input: String): String{
    val moves = input.map{it.toInt()}.toList() + listOf(17, 31, 73, 47, 23)
    var data = IntRange(0, 255).toList()
    var start = 0
    var skip = 0

    for(i in 0 until 64) {
        for (move in moves) {
            data = getData(data, start, move)
            start = (start + move + skip++) % data.size
        }
    }

    return data.chunked(16)
            .map{it.reduce{x, y -> x xor y}}
            .map{"%02x".format(it)}
            .joinToString("")
}


fun getData(data: List<Int>, start: Int, offset: Int ) : List<Int>{
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


