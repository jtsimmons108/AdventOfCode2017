package main.AoC2017

import java.io.File
import java.util.*


fun main(args: Array<String>) {


    val instructions = File("inputs/2017/day18.in").readLines()
    println("Part 1: ${Day18.solvePart1(instructions)}")
    println("Part 2: ${Day18.solvePart2(instructions)}")
}

object Day18 {

    fun solvePart1(instructions: List<String>): Long{
        val registers = mutableMapOf<String, Long>().withDefault { 0L }
        var played = mutableListOf<Long>()
        var index = 0
        
        while(index >= 0 && index < instructions.size){
            index = processInstructionPartOne(instructions[index], index, registers, played)
        }
        
        return played.last()
    }
    
    fun solvePart2(instructions: List<String>): Long{
        val registerOne = mutableMapOf<String, Long>("p" to 0, "sent" to 0, "waiting" to 0)
        val registerTwo = mutableMapOf<String, Long>("p" to 1, "sent" to 0, "waiting" to 0)
        val registerOneQueue: Deque<Long> = LinkedList()
        val registerTwoQueue: Deque<Long> = LinkedList()
        var indexOne = 0
        var indexTwo = 0

        while (registerOne["waiting"] != 1L || registerTwo["waiting"] != 1L) {
            indexOne = processInstructionPartTwo(instructions[indexOne], indexOne, registerOne, registerOneQueue, registerTwoQueue)
            indexTwo = processInstructionPartTwo(instructions[indexTwo], indexTwo, registerTwo, registerTwoQueue, registerOneQueue)
        }

        return registerTwo.getValue("sent")
    }

    fun processInstructionPartOne(instruction: String, index: Int, 
                                  registers: MutableMap<String, Long>, played: MutableList<Long>): Int {
        
        var values = instruction.split(" ")
        val move = values.first()
        values = values.drop(1)

        if (move == "jgz") {
            val (check, offset) = values.map { getValue(it, registers) }
            if (check > 0)
                return index + offset.toInt()
        } else if (move == "snd") {
            played.add(getValue(values.first(), registers))
        } else if (move == "rcv") {
            val value = getValue(values.first(), registers)
            if(value != 0L) {
                return Int.MAX_VALUE
            }
        } else {
            val reg = values.first()
            val value = getValue(values.last(), registers)
            when (move) {
                "set" -> registers[reg] = value
                "add" -> registers[reg] = registers.getValue(reg) + value
                "mul" -> registers[reg] = registers.getValue(reg) * value
                "mod" -> registers[reg] = registers.getValue(reg) % value
            }
        }
        return index + 1

    }
    
    fun processInstructionPartTwo(instruction: String, index: Int, registers: MutableMap<String, Long>,
                           queue: Deque<Long>, otherQueue: Deque<Long>): Int {
        var values = instruction.split(" ")
        val move = values.first()
        values = values.drop(1)

        if (move == "jgz") {
            val (check, offset) = values.map { getValue(it, registers) }
            if (check > 0)
                return index + offset.toInt()
        } else if (move == "snd") {
            otherQueue.addLast(getValue(values.first(), registers))
            registers["sent"] = registers.getValue("sent") + 1
        } else if (move == "rcv") {
            if (queue.size > 0) {
                registers[values.first()] = queue.removeFirst()
                registers["waiting"] = 0
            } else {
                registers["waiting"] = 1
                return index
            }
        } else {
            val reg = values.first()
            val value = getValue(values.last(), registers)
            when (move) {
                "set" -> registers[reg] = value
                "add" -> registers[reg] = registers.getValue(reg) + value
                "mul" -> registers[reg] = registers.getValue(reg) * value
                "mod" -> registers[reg] = registers.getValue(reg) % value
            }
        }
        return index + 1

    }

    fun getValue(value: String, registers: MutableMap<String, Long>): Long {
        if (registers.containsKey(value))
            return registers.getValue(value)
        return value.toLong()
    }

    
}
