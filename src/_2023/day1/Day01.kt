package _2023.day1

import compareOrThrow
import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { inputLine ->
            inputLine.mapNotNull {
                it.digitToIntOrNull()
            }.let {
                "${it.first()}${it.last()}".toInt()
            }
        }
    }


    fun part2(input: List<String>): Int {
        return input.sumOf { inputLine ->
            val list = parseNumbers(inputLine)
            "${list.first()}${list.last()}".toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(path = "src/_2023/day1/Day01_test.txt")
    check(part2(testInput) == 281)

    // More tests
    compareOrThrow(parseNumbers("eightwothree"), listOf(8, 2, 3))
    compareOrThrow(parseNumbers("eight8wothree"), listOf(8, 8, 3))
    compareOrThrow(parseNumbers("twoeighthree"), listOf(2, 8, 3))
    compareOrThrow(parseNumbers("twothreeight"), listOf(2, 3, 8))
    compareOrThrow(parseNumbers("two3threeight"), listOf(2, 3, 3, 8))

    // Actual data
    val input = readInput(path = "src/_2023/day1/Day01.txt")
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