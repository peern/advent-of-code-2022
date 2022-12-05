package advent_of_code.day1

import advent_of_code.getReaderFromResourceFile
import kotlin.streams.asSequence

class CaloryCounting(fileName: String = "day1_input.txt") {

    private val elves = mutableListOf<Elf>()

    init {
        initializeElvesFromFile(fileName)
    }

    private fun initializeElvesFromFile(fileName: String) {
        getReaderFromResourceFile(fileName)?.lines()?.asSequence()
            ?.forEach {
                if (it.isBlank()) {
                    elves.add(Elf())
                }
                if (it.isNotBlank()) {
                    if (elves.isEmpty()) {
                        elves.add(Elf())
                    }
                    elves.last().addCalories(it.toInt())
                }
            }
    }

    private fun getElfWithMostCalories(): Elf? {
        return elves.stream()
            .reduce { acc, elf -> if (acc.getCarriedCalories() < elf.getCarriedCalories()) elf else acc }
            .orElse(null)
    }

    fun getPositionOfElfWithMostCalories(): Int {
        val elfIndex = elves.indexOf(getElfWithMostCalories())
        return if (elfIndex > -1) elfIndex + 1 else elfIndex
    }

    fun findCaloriesOfElfCarryingMostCalories(): Int? {
        return getElfWithMostCalories()?.getCarriedCalories()
    }

    fun findCaloriesOfTopNElvesCarryingMostCalories(topN: Long): Int {
        return elves.stream()
            .mapToInt { it.getCarriedCalories() }
            .map { -it }
            .sorted()
            .map { -it }
            .limit(topN)
            .sum()
    }

}