package advent_of_code.day3

import advent_of_code.getReaderFromResourceFile
import kotlin.streams.asSequence

class RucksackReorganization(fileName: String = "day3_rucksack_test.txt") {

    private val rucksacks: MutableList<Rucksack>

    init {
        this.rucksacks = getReaderFromResourceFile(fileName).lines().asSequence()
            .map { line ->
                Rucksack(
                    line.substring(0, line.length / 2).map { RucksackItem(it) },
                    line.substring(line.length / 2).map { RucksackItem(it) }
                )
            }.toMutableList()

    }

    fun sumOfPrioritiesOfItemsAppearingTwice(): Int {
        return this.rucksacks.asSequence()
            .map { it.findItemIncludedInAllCompartments()?.priority ?: 0 }
            .sum()
    }

    private fun getCommonItemsOfRucksackGroupsOfThree(): List<RucksackItem> {
        return this.rucksacks
            .windowed(3, 3)
            .map { rucksacksOfGroup ->
                rucksacksOfGroup
                    .map { it.getFirstCompartment().items.toSet().union(it.getSecondCompartment().items) }
                    .reduce { acc, rucksackItems -> acc.intersect(rucksackItems) }
            }.map { it.first() }
    }

    fun getBadgePrioritySum(): Int {
        return getCommonItemsOfRucksackGroupsOfThree()
            .sumOf { it.priority }
    }
}