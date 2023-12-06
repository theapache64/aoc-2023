import java.io.File
import kotlin.io.path.readLines

abstract class BaseDay<T,D> {
    fun start() {
        val dayNo = this::class.simpleName?.replace("Day", "") ?: error("couldn't find day number from class name")
        println("🙏 Welcome to AoC 2023: Day$dayNo ")
        // Part 1


        println("📖 parsing test input... ")
        val part1TestInput = readInput(path = "src/day$dayNo/Day${dayNo}_part01_test.txt")
        val parsedTestInput = parse(part1TestInput)
        println("🤖 processing input(testInput)..." )
        compare(part1(parsedTestInput), part1TestAnswer)
        println("📖 parsing actual input... ")
        val input = parse(readInput(path = "src/day$dayNo/Day${dayNo}.txt"))
        println("🤖 processing input(actualInput)..." )
        part1(input).println()

        // Part 2
        println("🤖 preparing for part 2..." )
        val part2TestFile = File("src/day$dayNo/Day${dayNo}_part02_test.txt")
        if(part2TestFile.exists()){
            val part2TestInput = part2TestFile.toPath().readLines()
            val parsedInput = parse(part2TestInput)
            compare(part2(parsedInput), part2TestAnswer)
            part2(input).println()
        }else{
            println("🤖 part 2 skipped. no test file found")
        }
    }

    abstract val part1TestAnswer: D
    abstract val part2TestAnswer: D
    abstract fun parse(input: List<String>): T
    abstract fun part1(input: T): D
    abstract fun part2(input: T): D

    companion object {
        private fun <D> compare(actual: D, expected: D) {
            if (actual == expected){
                println("AoC : $expected is the right answer ✅")
                return
            }
            error("Wrong answer: Expected '$expected', but actual is '$actual'")
        }
    }
}