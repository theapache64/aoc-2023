abstract class BaseDay<T> {
    fun start() {
        val dayNo = this::class.simpleName?.replace("Day", "") ?: error("couldn't find day number from class name")
        println("QuickTag: BaseDay:start: $dayNo")

        val input = readInput(path = "src/day$dayNo/Day${dayNo}.txt")

        // Part 1
        val part1TestInput = readInput(path = "src/day$dayNo/Day${dayNo}_part01_test.txt")
        val parsedTestInput = parse(part1TestInput)
        compare(part1(parsedTestInput), part1TestAnswer)
        part1(parsedTestInput).println()

        // Part 2
        val part2TestInput = readInput(path = "src/day$dayNo/Day${dayNo}_part02_test.txt")
        val parsedInput = parse(part2TestInput)
        compare(part2(parsedInput), part2TestAnswer)
        part2(parsedInput).println()
    }

    abstract val part1TestAnswer: Int
    abstract val part2TestAnswer: Int
    abstract fun parse(input: List<String>): T
    abstract fun part1(input: T): Int
    abstract fun part2(input: T): Int

    companion object {
        private fun compare(actual: Int, expected: Int) {
            if (actual == expected){
                println("AoC : $expected is the right answer ✅")
                return
            }
            error("Wrong answer: Expected '$expected', but actual is '$actual'")
        }
    }
}