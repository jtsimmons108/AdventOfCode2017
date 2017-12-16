package main.AoC2015

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main(args: Array<String>){

    var gen_a = 703L
    var gen_b = 516L
    val factor = 2147483647L
    val factor_a = 16807L
    val factor_b = 48271L
    var count = 0
    var total = 0

    for(i in 1..40000000){
        gen_a = (gen_a * factor_a) % factor
        gen_b = (gen_b * factor_b) % factor
        if((gen_a and 0xffff) == (gen_b and 0xffff))
            total++
    }
    println("Part 1: $total")

    total = 0
    gen_a = 703L
    gen_b = 516L

    while(count < 5000000){
        while(gen_a % 4 != 0L){
            gen_a = (gen_a * factor_a) % factor
        }
        while(gen_b % 8 != 0L){
            gen_b = (gen_b * factor_b) % factor
        }

        if((gen_a and 0xffff) == (gen_b and 0xffff))
            total++

        count++
        gen_a = (gen_a * factor_a) % factor
        gen_b = (gen_b * factor_b) % factor
    }

    println("Part 2: $total")

}


