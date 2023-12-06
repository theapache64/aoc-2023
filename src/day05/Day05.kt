package day05

import BaseDay
import kotlin.system.exitProcess

fun main() {
    Day05().start()
}

class Day05 : BaseDay() {
    override val part1TestAnswer: Int get() = 35
    override val part2TestAnswer: Int get() = 35

    override fun part1(testInput: List<String>): Int {
        (50..97).forEach {
            print("$it, ")
        }
        println()
        (52..99).forEach {
            print("$it, ")
        }
        exitProcess(0)
        return -1
    }

    override fun part2(testInput: List<String>): Int {
        return -1
    }
}