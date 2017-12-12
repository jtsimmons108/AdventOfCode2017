package main

import sun.jvm.hotspot.oops.CellTypeState.top
import java.io.File
import java.util.regex.Pattern

val nodes = HashMap<String, Program>()
fun main(args: Array<String>) {

    val input = File("inputs/day7.in").readLines()
    val nameAndWeight = Regex("(\\w+)\\s\\((\\d+)\\)")
    val children = Regex("(\\w+)\\s\\((?:\\d+)\\)(?: -> )?([\\w\\,\\s]*)")


    input.map {
        val groups = nameAndWeight.find(it)!!.groupValues
        nodes[groups[1]] = Program(groups[1], groups[2].toInt())
    }

    input.map{
        val groups = children.find(it)!!.groupValues
        if(groups[2].isNotEmpty()){
            val parentNode = nodes.getValue(groups[1])
            val childNodes = groups[2].split(", ").map{nodes.getValue(it)}.toList()
            parentNode.children = childNodes
            childNodes.map { it.parent = parentNode }
        }
    }

    var top = nodes.values.filter { it.parent == null }.first().name
    println("Part 1: $top")

    while(nodes.getValue(top).children.map{it.getTotalWeight()}.toSet().size != 1){
        val children = nodes.getValue(top).children
        val weights = children.map{it.getTotalWeight()}.toList()
        top = children.filter{ child: Program -> weights.count { it == child.getTotalWeight() } == 1}.first().name

    }
    val offProgram = nodes.getValue(top)
    println("Part 2: ${offProgram.name} should weigh ${offProgram.weight - 8}")


}

class Program(val name: String, val weight: Int, var parent: Program? = null, var children: List<Program> = emptyList()){

    fun getTotalWeight(): Int{
        return weight + children.map { it.getTotalWeight() }.sum()
    }

    override fun toString(): String {
        return "Program(name='$name', weight=$weight)"
    }


}