abstract class BaseDay {
    fun start() {
        val dayNo = this::class.simpleName?.replace("Day", "") ?: error("couldn't find day number from class name")
        println("QuickTag: BaseDay:start: $dayNo")

        val input = readInput(path = "src/day$dayNo/Day${dayNo}.txt")

        // Part 1
        val part1TestInput = readInput(path = "src/day$dayNo/Day${dayNo}_part01_test.txt")
        compare(part1(part1TestInput), part1TestAnswer)
        part1(input).println()

        // Part 2
        val part2TestInput = readInput(path = "src/day$dayNo/Day${dayNo}_part02_test.txt")
        compare(part2(part2TestInput), part2TestAnswer)
        part2(input).println()
    }

    abstract val part1TestAnswer: Int
    abstract val part2TestAnswer: Int
    abstract fun part1(testInput: List<String>): Int
    abstract fun part2(testInput: List<String>): Int

    companion object {
        private fun compare(actual: Int, expected: Int) {
            println("Wrong answer: Expected '$expected', but actual is '$actual'")
        }
    }
}