package main.AoC2017

import kotlin.system.measureTimeMillis

fun main(args: Array<String>){

    var index = 0
    var state = 'A'
    val nums = mutableMapOf<Int, Int>().withDefault { 0 }
    
    repeat(12172063) {
        if (state == 'A') {
            if (nums.getValue(index) == 0) {
                nums[index] = 1
                index += 1
                state = 'B'
            }
            else {
                nums[index] = 0
                index -= 1
                state = 'C'
            }
        }
        else if (state == 'B') {
            if (nums.getValue(index) == 0) {
                nums[index] = 1
                index -= 1
                state = 'A'
            }
            else {
                nums[index] = 1
                index -= 1
                state = 'D'
            }
        }
        else if (state == 'C') {
            if (nums.getValue(index) == 0) {
                nums[index] = 1
                index += 1
                state = 'D'
            }
            else {
                nums[index] = 0
                index += 1
                state = 'C'
            }
        }
        else if (state == 'D') {
            if (nums.getValue(index) == 0) {
                nums[index] = 0
                index -= 1
                state = 'B'
            }
            else {
                nums[index] = 0
                index += 1
                state = 'E'
            }
        }
        else if (state == 'E') {
            if (nums.getValue(index) == 0) {
                nums[index] = 1
                index += 1
                state = 'C'
            }
            else {
                nums[index] = 1
                index -= 1
                state = 'F'
            }
        }
        else {
            if (nums.getValue(index) == 0) {
                nums[index] = 1
                index -= 1
                state = 'E'
            }
            else {
                nums[index] = 1
                index += 1
                state = 'A'
            }
        }
    }
    println("Part 1: ${nums.values.sum()}")
    
}

