abstract class BaseDay {
    fun start() {
        val dayNo = this::class.simpleName?.replace("Day","") ?:
        error("couldn't find day number from class name")
        println("QuickTag: BaseDay:start: $dayNo")

        // test if implementation meets criteria from the description, like:
        val testInput = readInput(path = "src/_2023/day$dayNo/Day${dayNo}_test.txt")
        check(part2(testInput) == 30)


        // More tests

        // Actual data
        val input = readInput(path = "src/_2023/day$dayNo/Day${dayNo}.txt")
        part1(input).println()
        part2(input).println()
    }

    abstract val testInputAnswer: Int
    abstract fun part1(testInput: List<String>): Int
    abstract fun part2(testInput: List<String>): Int
}