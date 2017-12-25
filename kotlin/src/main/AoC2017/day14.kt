package main.AoC2017

import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {

    val grid = mutableListOf<MutableList<Int>>()

    (0..127).mapTo(grid) { num ->
        getBinaryString(getKnotHash("jxqlasbh-$num"))
                .map { it.toInt() - '0'.toInt() }
                .toMutableList()
    }
    val total = grid.map { it.count { i -> i == 1 } }.sum()
    println("Part 1: $total")

    var group = 1
    for (r in grid.indices) {
        for (c in grid[0].indices) {
            if (grid[r][c] == 1) {
                group++
                markNeighbors(r, c, grid, group)
            }
        }
    }

    println("Part 2: ${group - 1}")
}

fun markNeighbors(row: Int, column: Int, grid: MutableList<MutableList<Int>>, group: Int) {
    grid[row][column] = group

    if (row > 0 && grid[row - 1][column] == 1) {
        markNeighbors(row - 1, column, grid, group)
    }
    if (row < grid.size - 1 && grid[row + 1][column] == 1) {
        markNeighbors(row + 1, column, grid, group)
    }
    if (column > 0 && grid[row][column - 1] == 1) {
        markNeighbors(row, column - 1, grid, group)
    }
    if (column < grid[row].size - 1 && grid[row][column + 1] == 1) {
        markNeighbors(row, column + 1, grid, group)
    }

}


fun getBinaryString(input: String): String {
    return input.map { Integer.parseInt(it.toString(), 16) }.map { it.toString(2).padStart(4, '0') }.joinToString("");
}


