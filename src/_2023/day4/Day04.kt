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
                        if (cardPoints <= 1) {
                            cardPoints++
                        } else {
                            cardPoints *= 2
                        }
                    }
                }
            }
            totalPoints += cardPoints
        }
        return totalPoints
    }

    fun part2(cards: List<String>): Int {
        val cardsMap = cards.associateWith { 1 }.toMutableMap()
        var cardProcessed = 0
        while (true) {
            val cardData = cardsMap.entries.find { it.value != 0 } ?: break
            val card = cardData.key
            val cardCount = cardData.value
            cardsMap[card] = cardCount - 1
            val noOfWins = getNoOfWins(card)
            cardProcessed++

            if (noOfWins > 0) {
                // update new card counts
                val cardIndex = cardsMap.keys.indexOf(card)
                val subCardKeys = cardsMap.keys.toList().subList(cardIndex + 1, cardIndex + noOfWins + 1)
                for (subCardKey in subCardKeys) {
                    cardsMap[subCardKey] = cardsMap[subCardKey]?.plus(1)!!
                }
            }


        }
        return cardProcessed
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(path = "src/_2023/day4/Day04_test.txt")
    check(part2(testInput) == 30)


    // More tests

    // Actual data
    val input = readInput(path = "src/_2023/day4/Day04.txt")
    part1(input).println()
    part2(input).println()
}

fun getNoOfWins(card: String): Int {
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

    var noOfWins = 0
    for (winningNumber in winningNumbers) {
        for (myNumber in myNumbers) {
            if (winningNumber == myNumber) {
                noOfWins++
                print("$winningNumber ")
            }
        }
    }
    return noOfWins
}
