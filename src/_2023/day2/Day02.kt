package _2023.day2

import println
import readInput
import kotlin.system.exitProcess

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

            // checking if the game is impossible or not
            for (set in sets) {
                val colors = set.split(",")
                    .map { it.trim() }
                for (color in colors) {
                    val (pickedCount, pickedColor) = color.split(" ")
                    val cube = Cube.entries.toTypedArray().find {
                        it.color == pickedColor
                    } ?: error("couldn't find color $pickedColor -> '$color'")

                    if (pickedCount.toInt() > cube.max) {
                        // impossible game
                        gameIds.remove(gameId)
                        continue@gameLoop
                    }
                }
            }
        }
        return gameIds.sum()
    }


    fun part2(games: List<String>): Int {
        val gamePowers = mutableListOf<Int>()
        gameLoop@ for (game in games) {
            val gameResult = gameRegex.find(game)?.groupValues ?: error("Invalid data : '$game' ")
            val sets = gameResult[2].split(";")
            val gameMap = mutableMapOf<String, MutableList<Int>>()
            for (set in sets) {
                val colors = set.split(",").map { it.trim() }
                for (color in colors) {
                    val (pickedCount, pickedColor) = color.split(" ")
                    gameMap.getOrPut(pickedColor, defaultValue = { mutableListOf(pickedCount.toInt()) })
                        .add(pickedCount.toInt())
                }
            }
            val power = gameMap.values.map { it.max() }.reduce { acc, i -> acc * i }
            gamePowers.add(power)
        }
        return gamePowers.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(path = "src/_2023/day2/Day02_test.txt")
    check(part2(testInput) == 2286)

    println("QuickTag: :main: ------------")
    // More tests

    // Actual data
    val input = readInput(path = "src/_2023/day2/Day02.txt")
    part1(input).println()
    part2(input).println()
}

