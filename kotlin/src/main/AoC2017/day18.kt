package main.AoC2017

import java.io.File
import java.util.*


fun main(args: Array<String>) {


    val instructions = File("inputs/2017/day18.in").readLines()
    val registerOne = mutableMapOf<String, Long>("p" to 0, "sent" to 0, "waiting" to 0)
    val registerTwo = mutableMapOf<String, Long>("p" to 1, "sent" to 0, "waiting" to 0)
    val registerOneQueue: Deque<Long> = LinkedList()
    val registerTwoQueue: Deque<Long> = LinkedList()
    var indexOne = 0
    var indexTwo = 0

    while (registerOne["waiting"] != 1L || registerTwo["waiting"] != 1L) {
        indexOne = processInstruction(instructions[indexOne], indexOne, registerOne, registerOneQueue, registerTwoQueue)
        indexTwo = processInstruction(instructions[indexTwo], indexTwo, registerTwo, registerTwoQueue, registerOneQueue)
    }

    println(registerTwo["sent"])
}


fun processInstruction(instruction: String, index: Int, registers: MutableMap<String, Long>,
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


