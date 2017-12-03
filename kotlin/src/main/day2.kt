package main
import java.io.File

fun main(args: Array<String>){
    val inpt: List<String> = File("inputs/day2.in").readLines()

    var (total1, total2) = listOf(0, 0)

    for(line in inpt){
        val data = line.split("\t").map { num -> num.toInt() }.sorted()

        //Part1 - Subtract min value from max value
        total1 += data[data.size -1] - data[0]


        //Part2 - Find 2 numbers that are divisible and add their quotient
        for(i in data.indices){
            var j = data.size - 1
            while(j > i + 1 && data[j] % data[i] != 0){
                j--
            }
            if(data[j] % data[i] == 0){
                total2 += data[j] / data[i]
                break
            }
        }
    }

    println("Part1: $total1")
    println("Part2: $total2")
}