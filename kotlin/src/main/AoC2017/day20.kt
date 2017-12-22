package main.AoC2017

import java.io.File
import java.util.regex.Pattern
import kotlin.math.abs


fun main(args: Array<String>){
    
    val input = File("inputs/2017/day20.in").readLines()
    val pattern = Regex("p=<(.+)>, v=<(.+)>, a=<(.+)>")
    val particles = mutableListOf<Particle>()
    input.mapIndexed{ i, line -> 
        val groups = pattern.find(line)!!.groupValues
        particles.add(Particle(i, 
                                groups[1].split(",").map { it.toInt() }.toMutableList(),
                                groups[2].split(",").map { it.toInt() }.toMutableList(),
                                groups[3].split(",").map { it.toInt() }.toMutableList()))
    }
    
    particles.sortBy { it.getManhattanAcceleration() }
    println("Part 1: ${particles.first().id}")
    
    
}



class Particle (val id: Int, val pos: MutableList<Int>, val vel: MutableList<Int>, val acc: MutableList<Int>){
    
    
    fun getManhattanDistance(): Int{
        return pos.sumBy { abs(it) }
    }
    
    fun getManhattanAcceleration(): Int{
        return acc.sumBy { abs(it) }
    }

    override fun toString(): String {
        return "$id $pos $vel $acc"
    }
    
    
}