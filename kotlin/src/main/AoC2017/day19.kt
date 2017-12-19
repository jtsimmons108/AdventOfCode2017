package main.AoC2017

import java.io.File


fun main(args: Array<String>){
    
    
    val grid = File("inputs/2017/day19.in").readLines().map{it.toCharArray()}.toList()
    val SOUTH = 0
    val EAST = 1
    val NORTH = 2
    val WEST = 3
    var direction = SOUTH
    var row = 0
    var col = grid[0].indexOf('|')
    var result = ""
    var steps = 0
    while(grid[row][col] != ' ') {
        steps++
        if(grid[row][col].isLetter()){
            result += grid[row][col]
        }else if(grid[row][col] == '+'){
            direction = if(direction % 2 == 0){
                if(grid[row][col + 1] != ' '){
                    EAST
                }else{
                    WEST
                }
            }else{
                if(grid[row + 1][col] != ' '){
                    SOUTH
                }else{
                    NORTH
                }
            }
        }
        when(direction){
            SOUTH -> row++
            NORTH -> row--
            EAST  -> col++
            WEST  -> col--
        }

    }
    
    println("Part 1: $result")
    println("Part 2: $steps")
    
}