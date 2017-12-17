package main.AoC2017

import javax.sound.midi.MidiSystem.getSequence
import kotlin.coroutines.experimental.buildSequence

fun main(args: Array<String>){


    val gen_a = getSequence(703L, 16807L)
    val gen_b = getSequence(516L, 48271L)
    println("Part 1: ${gen_a.zip(gen_b)
            .take(40000000)
            .count{(it.first and 0xffff) == (it.second and 0xffff)}}")


    println("Part 2: ${gen_a.filter { it % 4 == 0L }
            .zip(gen_b.filter { it % 8 == 0L })
            .take(5000000)
            .count{(it.first and 0xffff) == (it.second and 0xffff)}}")
}


fun getSequence(start: Long, factor: Long, divisor: Long = 2147483647L): Sequence<Long>{

    return generateSequence((start * factor) % divisor){(it * factor) % divisor}

}