package advent_of_code.day3

class Rucksack(itemsFirstCompartment: List<RucksackItem>, itemsSecondCompartment: List<RucksackItem>) {
    private val compartments = Pair(RucksackCompartment(), RucksackCompartment())

    init {
        this.compartments.first.items.addAll(itemsFirstCompartment)
        this.compartments.second.items.addAll(itemsSecondCompartment)
    }

    fun getFirstCompartment(): RucksackCompartment {
        return this.compartments.first
    }

    fun getSecondCompartment(): RucksackCompartment {
        return this.compartments.second
    }

    fun findItemIncludedInAllCompartments(): RucksackItem? {
        return this.compartments.first.items
            .intersect(this.compartments.second.items.toSet())
            .firstOrNull()
    }
}