package day05

import BaseDay

fun main() {
    Thread {
        while(true){
            println("QuickTag: :main: ${Runtime.getRuntime().totalMemory()}")
            Thread.sleep(1000)
        }
    }.start()
    Day05().start()
}

data class Almanac(
    val seeds: List<Long>,
    val maps: List<AgriMap>
)

data class AgriMap(
    val name: String,
    val map: Map<Long, Long>
)

class Day05 : BaseDay<Almanac, Long>() {
    override val part1TestAnswer: Long get() = 35
    override val part2TestAnswer: Long get() = 0

    override fun parse(input: List<String>): Almanac {
        val seeds = input.first().split(": ")[1].split(" ").map { it.toLong() }
        val maps: List<AgriMap> = input.drop(2)
            .joinToString(separator = "\n")
            .split("\n\n")
            .map {
                val mapEntries = it.split("\n")
                val title = mapEntries.first()
                val mapData = mapEntries.drop(1)
                val map = mutableMapOf<Long, Long>()
                for (value in mapData) {
                    val (destination, source, range) = value.split(" ").map { num -> num.toLong() }
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

    override fun part1(almanac: Almanac): Long {
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

    override fun part2(input: Almanac): Long {
        return -1
    }
}