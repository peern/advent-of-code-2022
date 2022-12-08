package advent_of_code.day5

import advent_of_code.getReaderFromResourceFile

class SupplyStacks(private val fileName: String = "day5_supply_stacks_test.txt") {

    private val crateStacks: List<ArrayDeque<Char>>
    private val rearrangementProcedures: List<RearrangementProcedure>

    init {
        val rawInput = getReaderFromResourceFile(fileName).readText()
        val splits = rawInput.split("\n\n")

        val startingCrateStacksInput = splits.first()
        val rearrangementProceduresInput = splits.last()

        this.crateStacks = startingCrateStacksInput
            .lines()
            .last()
            .split(Regex("\\s+"))
            .filter { it.isNotBlank() }
            .map { ArrayDeque<Char>() }
            .toList()

        startingCrateStacksInput
            .lines()
            .take(startingCrateStacksInput.lines().size - 1)
            .map { it.plus(" ") }
            .map { it.chunkedSequence(4).map { crate -> crate.trim().removePrefix("[").removeSuffix("]") } }
            .forEach {
                it.zip(crateStacks.asSequence()).forEach { crateAndStack ->
                    if (crateAndStack.first.isNotBlank()) crateAndStack.second.addLast(crateAndStack.first.first())
                }
            }

        this.rearrangementProcedures = rearrangementProceduresInput
            .lines()
            .map { it.split(" ") }
            .map { RearrangementProcedure(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
    }

    fun performRearrangementsPart1(): List<ArrayDeque<Char>> {
        rearrangementProcedures.forEach { rearrangementProcedure ->
            repeat(rearrangementProcedure.amount) {
                this.crateStacks[rearrangementProcedure.to - 1]
                    .addFirst(this.crateStacks[rearrangementProcedure.from - 1].removeFirst())
            }
        }
        return this.crateStacks
    }

    fun performRearrangementsPart2(): String {
        val stacksList = this.crateStacks.map { it.toMutableList() }

        rearrangementProcedures.forEach { rearrangementProcedure ->
            val elementsToMove = stacksList[rearrangementProcedure.from - 1].subList(0, rearrangementProcedure.amount)
            stacksList[rearrangementProcedure.to - 1].addAll(0, elementsToMove)
            val newStack = stacksList[rearrangementProcedure.from - 1].drop(rearrangementProcedure.amount)
            stacksList[rearrangementProcedure.from - 1].clear()
            stacksList[rearrangementProcedure.from - 1].addAll(newStack)
        }

        return stacksList
            .map { it.first().toString() }
            .reduce { acc, s -> acc.plus(s) }
    }

    fun getTopElements(): String {
        return crateStacks
            .map { it.first().toString() }
            .reduce { acc, s -> acc.plus(s) }
    }


    data class RearrangementProcedure(val amount: Int, val from: Int, val to: Int)
}
