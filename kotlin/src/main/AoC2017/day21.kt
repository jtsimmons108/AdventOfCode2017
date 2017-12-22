package main.AoC2017

import java.io.File


fun main(args: Array<String>){
    
    val input = File("inputs/2017/day21.in").readLines()
    val artistBook = mutableMapOf<String, List<List<Int>>>()
    var grid = patternStringToMatrix(".#./..#/###")
    input.map { line -> 
            val (inputPattern, outputPattern) = line.split(" => ")
            val outputMatrix = patternStringToMatrix(outputPattern)
            val variations = getAllMatrixVariations(patternStringToMatrix(inputPattern))
            for(variation in variations){
                artistBook[matrixToPatternString(variation)] = outputMatrix
            }
    }

    repeat(18){
        val groupSize = if(grid.size % 2 == 0) 2 else 3
        val groups = grid.size / groupSize
        val newGrid = List(groups * (groupSize + 1)){_ -> mutableListOf<Int>()}
        for(r in 0 until groups){
            for(c in 0 until groups){
                val currentGroup = grid
                        .subList(r * groupSize,(r + 1) * groupSize)
                        .map { it.subList(c * groupSize, (c + 1) * groupSize) }
                val output = artistBook.getValue(matrixToPatternString(currentGroup))
                for(i in output.indices){
                    newGrid[i + (groupSize + 1) * r].addAll(output[i])
                }
            }
        }
        grid = newGrid.map { it.toList() }

    }
    println(grid.sumBy { it.sum() })
 
}



fun rot90(matrix: List<List<Int>>): List<List<Int>>{
    return transpose(matrix.asReversed())
}

fun transpose(matrix: List<List<Int>>): List<List<Int>>{
    val size = matrix.size
    val newList = MutableList(size){ _ -> MutableList<Int>(size){ _ -> 0} }
    for(i in 0 until size){
        for(j in 0 until size){
            newList[j][i] = matrix[i][j]
        }
    }
    return newList.map { it.toList() }.toList()
}

fun flipUD(matrix: List<List<Int>>): List<List<Int>>{
    val newList = matrix.map { it.toMutableList() }
    for(r in 0 until matrix.size){
        for(c in 0 until matrix.size){
            newList[matrix.size - 1 - r][c] = matrix[r][c]
        }
    }
    return newList.map { it.toList() }
}

fun flipLR(matrix: List<List<Int>>): List<List<Int>>{
    val newList = matrix.map { it.toMutableList() }
    for(r in 0 until matrix.size){
        for(c in 0 until matrix.size){
            newList[r][matrix.size - 1 - c] = matrix[r][c]
        }
    }
    return newList.map { it.toList() }
}

fun getAllMatrixVariations(matrix: List<List<Int>>): Set<List<List<Int>>>{
    val set = mutableSetOf(matrix)
    var current = matrix
    for(i in 0 until 4){
        set.add(current)
        set.add(flipLR(current))
        set.add(flipUD(current))
        current = rot90(current)
    }
    return set
}

fun matrixToPatternString(matrix: List<List<Int>>): String{
    return matrix.joinToString("/") { it.map { i -> if(i == 1) '#' else '.' }.joinToString("") }
}

fun patternStringToMatrix(pattern: String): List<List<Int>>{
    return pattern.split("/").map { it.map { if (it == '#') 1 else 0} }
}