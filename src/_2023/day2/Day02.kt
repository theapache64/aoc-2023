package _2023.day2

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return -1
    }


    fun part2(input: List<String>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(path = "src/_2023/day2/Day02_test.txt")
    check(part2(testInput) == 281)

    // More tests

    // Actual data
    val input = readInput(path = "src/_2023/day2/Day02.txt")
    part1(input).println()
    part2(input).println()
}


private fun parseNumbers(line: String): List<Int> {
    val numbers = mutableListOf<Int>()
    var p1 = 0
    for ((y, i) in (0..line.length).withIndex()) {
        val word = line.substring(p1, y)
        val number = getEndsWithInt(word)
        if (number != null) {
            p1 = y - 1
            numbers.add(number)
        }
    }
    return numbers
}


private fun getEndsWithInt(word: String): Int? {
    return when {
        word.endsWith("one") || word.endsWith("1") -> 1
        word.endsWith("two") || word.endsWith("2") -> 2
        word.endsWith("three") || word.endsWith("3") -> 3
        word.endsWith("four") || word.endsWith("4") -> 4
        word.endsWith("five") || word.endsWith("5") -> 5
        word.endsWith("six") || word.endsWith("6") -> 6
        word.endsWith("seven") || word.endsWith("7") -> 7
        word.endsWith("eight") || word.endsWith("8") -> 8
        word.endsWith("nine") || word.endsWith("9") -> 9
        else -> null
    }
}