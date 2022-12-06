package advent_of_code.day4

import advent_of_code.getReaderFromResourceFile
import kotlin.streams.asSequence

class CampCleanup(private val fileName: String = "day4_camp_cleanup_test.txt") {

    fun getCountOfFullyContainedPairs(): Int {
        return getRangePairs()
            .count { it.first.containsAll(it.second) || it.second.containsAll(it.first) }
    }

    private fun getRangePairs(): Sequence<Pair<Set<Int>, Set<Int>>> {
        return getReaderFromResourceFile(fileName).lines().asSequence()
            .map {
                val ranges = it.split(",")

                val startAndEnd1 = ranges.first().split("-")
                val startAndEnd2 = ranges.last().split("-")

                val range1 = IntRange(startAndEnd1.first().toInt(), startAndEnd1.last().toInt())
                val range2 = IntRange(startAndEnd2.first().toInt(), startAndEnd2.last().toInt())

                Pair(range1.toSortedSet(), range2.toSortedSet())
            }
    }

    fun getCountOfOverlappingPairs(): Int {
        return getRangePairs()
            .count { it.first.intersect(it.second).isNotEmpty() }
    }
}