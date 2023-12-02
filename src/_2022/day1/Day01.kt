package _2022.day1

import println
import readInput

private fun getCalSum(input: List<String>): List<Int> {
    val calSum = mutableListOf<Int>()
    var tempCal = 0
    for (line in input) {
        if (line.isBlank()) {
            calSum.add(tempCal)
            tempCal = 0
            continue
        }
        tempCal += line.toInt()
    }
    return calSum
}

fun main() {
    fun part1(input: List<String>): Int {
        return getCalSum(input).max()
    }


    fun part2(input: List<String>): Int {
        return getCalSum(input).sortedDescending().subList(0, 3)
            .sum()
    }


    // Actual data
    val input = readInput(path = "src/_2022/day1/Day01.txt")
    part1(input).println()
    part2(input).println()
}

