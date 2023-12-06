package day05

import BaseDay

fun main() {
    Day05().start()
}

data class Almanac(
    val seeds: List<Int>,
    val maps: List<AgriMap>
)

data class AgriMap(
    val name: String,
    val map: Map<Int, Int>
)

class Day05 : BaseDay<Almanac>() {
    override val part1TestAnswer: Int get() = 35
    override val part2TestAnswer: Int get() = 0

    override fun parse(input: List<String>): Almanac {
        val seeds = input.first().split(": ")[1].split(" ").map { it.toInt() }
        val maps: List<AgriMap> = input.drop(2)
            .joinToString(separator = "\n")
            .split("\n\n")
            .map {
                val mapEntries = it.split("\n")
                val title = mapEntries.first()
                val mapData = mapEntries.drop(1)
                val map = mutableMapOf<Int, Int>()
                for (value in mapData) {
                    val (destination, source, range) = value.split(" ").map { num -> num.toInt() }
                    val destRange = (destination..<(destination + range)).toList()
                    val sourceRange = (source..<(source + range)).toList()
                    for ((index, src) in sourceRange.withIndex()) {
                        if(map.containsKey(src)){
                            error("map already has a value for '$src' -> ${map[src]}")
                        }
                        map[src] = destRange[index]
                    }
                }

                AgriMap(
                    name = title,
                    map = map
                )
            }
        return Almanac(
            seeds = seeds,
            maps = maps
        )
    }

    fun List<String>.splitListByPredicate(predicate: (String) -> Boolean): List<List<String>> {
        return groupBy { predicate(it) }
            .values
            .toList()
    }

    override fun part1(almanac: Almanac): Int {
        return almanac
            .seeds
            .map { seed ->
                var x = seed
                for(map in almanac.maps){
                    println("QuickTag: Day05:part1: searching in '${map.name}'")
                    x = map.map[x] ?: x
                }
                x
            }
            .also {
                println("QuickTag: locations : $it")
            }
            .min() // lowest
    }

    override fun part2(input: Almanac): Int {
        return -1
    }
}