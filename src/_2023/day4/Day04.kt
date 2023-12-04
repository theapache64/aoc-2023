package _2023.day4

import println
import readInput

fun main() {

    fun part1(cards: List<String>): Int {
        var totalPoints = 0
        for (card in cards) {
            var cardPoints = 0
            val (winningNumbers, myNumbers) = card
                .split(":")[1]
                .split("|")
                .map {
                    it.trim()
                        .split(" ")
                        .filter { number -> number.isNotBlank() }
                        .map { number ->
                            number.trim().toInt()
                        }
                }

            for (winningNumber in winningNumbers) {
                for (myNumber in myNumbers) {
                    if (winningNumber == myNumber) {
                        if (cardPoints < 1) {
                            cardPoints++
                        } else {
                            cardPoints *= cardPoints
                        }
                    }
                }
            }
            println("QuickTag: :part1: $totalPoints")
            totalPoints += cardPoints
        }
        return totalPoints
    }

    fun part2(lines: List<String>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(path = "src/_2023/day4/Day04_test.txt")
    check(part1(testInput) == 13)


    // More tests

    // Actual data
    val input = readInput(path = "src/_2023/day4/Day04.txt")
    part1(input).println()
    part2(input).println()
}

