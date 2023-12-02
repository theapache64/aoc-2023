package _2023.day2

import println
import readInput

private val gameRegex = "Game (?<gameId>\\d+):(?<sets>.+)".toRegex()


enum class Cube(
    val color: String,
    val max: Int
) {
    Red("red", 12),
    Green("green", 13),
    Blue("blue", 14)
}

fun main() {
    fun part1(games: List<String>): Int {
        val gameIds = mutableSetOf<Int>()
        gameLoop@ for (game in games) {
            val gameResult = gameRegex.find(game)?.groupValues ?: error("Invalid data : '$game' ")
            val gameId = gameResult[1].toInt()
            val sets = gameResult[2].split(";")
            gameIds.add(gameId)

            for (set in sets) {
                val colors = set.split(",")
                    .map { it.trim() }
                for (color in colors) {
                    val (pickedCount, pickedColor) = color.split(" ")
                    val cube = Cube.entries.toTypedArray().find {
                        it.color == pickedColor
                    } ?: error("couldn't find color $pickedColor -> '$color'")

                    if (pickedCount.toInt() > cube.max) {
                        println("QuickTag: :part1: wrong game -> '$game' because ${cube.color} can only go ${cube.max}, but found $pickedCount")
                        gameIds.remove(gameId)
                        continue@gameLoop
                    }
                }
            }
            println("QuickTag: :part1: good game -> $game")
        }
        println("QuickTag: :part1: $gameIds")
        return gameIds.sum()
    }


    fun part2(input: List<String>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(path = "src/_2023/day2/Day02_test.txt")
    check(part1(testInput) == 8)

    println("QuickTag: :main: ------------")
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