package advent_of_code.day3

data class RucksackItem(val item: Char) {

    val priority: Int = initPriority(item)

    private fun initPriority(item: Char): Int {
        val charValue = Character.getNumericValue(item) - 9
        return when (item.isLowerCase()) {
            true -> charValue
            false -> charValue + 26
        }
    }
}
