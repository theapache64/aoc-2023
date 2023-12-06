package day03

import println
import readInput

fun main() {
    fun isEnginePart(
        lineIndex: Int,
        lines: List<String>,
        digitStartIndex: Int,
        charIndex: Int,
        line: String,
        partNumbers: MutableList<Int>,
        digit: Int,
        isEdgeDigit: Boolean
    ): Boolean {
        val lineStartIndex = if (lineIndex == 0) 0 else lineIndex - 1
        val lineEndIndex = if (lineIndex == lines.lastIndex) lines.lastIndex else lineIndex + 1
        val interestedLines = lines.subList(lineStartIndex, lineEndIndex + 1)
        var found = false
        lineLoop@ for ((iLineIndex, iLine) in interestedLines.withIndex()) {
            val charStartIndex = if (digitStartIndex == 0) 0 else digitStartIndex - 1
            val charEndIndex = if (charIndex == line.lastIndex || isEdgeDigit) charIndex else charIndex + 1
            val lineSub = iLine.substring(charStartIndex, charEndIndex)
            if (lineSub.hasSymbol()) {
                partNumbers.add(digit)
                found = true
                break@lineLoop
            }
        }
        return found
    }

    fun part1(lines: List<String>): Int {
        val partNumbers = mutableListOf<Int>()
        val digits = mutableListOf<Int>()
        for ((lineIndex, line) in lines.withIndex()) {
            var digitStartIndex = -1
            for ((charIndex, char) in line.withIndex()) {


                if (char.isDigit() && digitStartIndex == -1) {
                    // digit started
                    digitStartIndex = charIndex

                    if (charIndex == line.lastIndex) {
                        // last single digit
                        val digit = line[charIndex].digitToInt()
                        digits.add(digit)
                        isEnginePart(
                            lineIndex,
                            lines,
                            digitStartIndex,
                            charIndex,
                            line,
                            partNumbers,
                            digit,
                            isEdgeDigit = false
                        )
                    }
                } else {

                    val isEdgeDigit = char.isDigit() && charIndex == line.lastIndex && digitStartIndex != -1
                    if (
                        (!char.isDigit() && digitStartIndex != -1) ||
                        isEdgeDigit
                    ) {
                        // digit ends
                        var realCharIndex = charIndex
                        if (isEdgeDigit) {
                            realCharIndex = charIndex + 1
                        }
                        val digit = line.substring(digitStartIndex, realCharIndex).toInt()
                        digits.add(digit)

                        // check symbol around
                        isEnginePart(
                            lineIndex,
                            lines,
                            digitStartIndex,
                            realCharIndex,
                            line,
                            partNumbers,
                            digit,
                            isEdgeDigit
                        )
                        digitStartIndex = -1
                    }

                }
            }
        }
        
        return partNumbers.sum()
    }


    fun getFullNumberAt(line: String, charIndex: Int): Int? {
        val knownChar = line[charIndex]
        if (!knownChar.isDigit()) return null
        var fullNumber = "$knownChar"
        // going left first
        if (charIndex != 0) {
            for (leftIndex in (0..<charIndex).reversed()) {
                val char = line[leftIndex]
                if (char.isDigit()) {
                    fullNumber = "$char$fullNumber"
                } else {
                    break
                }
            }
        }

        // going right side
        if (charIndex != line.lastIndex) {
            for (rightIndex in (charIndex + 1)..line.lastIndex) {
                val char = line[rightIndex]
                if (char.isDigit()) {
                    fullNumber = "$fullNumber$char"
                } else {
                    break
                }
            }
        }


        return fullNumber.toInt()
    }

    fun part2(lines: List<String>): Int {
        var sum = 0
        for ((lineIndex, line) in lines.withIndex()) {
            gearLoop@ for ((charIndex, char) in line.withIndex()) {
                if (char == '*') {
                    // it's a gear. let's get interested lines
                    val lineStartIndex = if (lineIndex == 0) 0 else lineIndex - 1
                    val lineEndIndex = if (lineIndex == lines.lastIndex) lines.lastIndex else lineIndex + 1
                    val interestedLines = lines.subList(lineStartIndex, lineEndIndex + 1)


                    val adjNumbers = mutableSetOf<Int>()
                    for (iLine in interestedLines) {

                        // left
                        val leftCharIndex = if (charIndex == 0) 0 else charIndex - 1
                        val leftChar = iLine.getOrNull(leftCharIndex)
                        if (leftChar?.isDigit() == true) {
                            val fullNumber = getFullNumberAt(iLine, leftCharIndex)
                            if (fullNumber != null) {
                                adjNumbers.add(fullNumber)
                            }

                            if (adjNumbers.size > 2) {
                                continue@gearLoop
                            }
                        }

                        // right
                        val rightCharIndex = if (charIndex == iLine.lastIndex) iLine.lastIndex else charIndex + 1
                        val rightChar = iLine.getOrNull(rightCharIndex)
                        if (rightChar?.isDigit() == true) {
                            val fullNumber = getFullNumberAt(iLine, rightCharIndex)
                            if (fullNumber != null) {
                                adjNumbers.add(fullNumber)
                            }

                            if (adjNumbers.size > 2) {
                                continue@gearLoop
                            }
                        }

                        // current position
                        val currentChar = iLine.getOrNull(charIndex)
                        if (currentChar?.isDigit() == true) {
                            val lx = if (charIndex == 0) 0 else charIndex - 1
                            val rx = if (charIndex == iLine.lastIndex) iLine.lastIndex else charIndex + 1
                            if (
                                iLine.getOrNull(lx)?.isDigit() == false &&
                                iLine.getOrNull(rx)?.isDigit() == false
                            ) {
                                adjNumbers.add(currentChar.digitToInt())

                                if (adjNumbers.size > 2) {
                                    continue@gearLoop
                                }
                            }
                        }
                    }

                    if (adjNumbers.size != 2) {
                        adjNumbers.clear()
                        adjNumbers.add(0)
                    }

                    
                    sum += adjNumbers
                        .reduce { acc, i -> acc * i }.also {
                            
                        }
                }
            }
        }
        
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(path = "src/_2023/day3/Day03_test.txt")
    check(part2(testInput) == 467835)

    
    // More tests

    // Actual data
    val input = readInput(path = "src/_2023/day3/Day03.txt")
    part1(input).println()
    part2(input).println()
}

private fun String.hasSymbol(): Boolean {
    return this.find { it.isSymbol() } != null
}

private fun Char?.isSymbol(): Boolean {
    if (this == null) return false
    return this != '.' && !isDigit()
}

