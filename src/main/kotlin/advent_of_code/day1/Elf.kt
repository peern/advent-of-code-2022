package advent_of_code.day1

class Elf {
    private var foodItems = mutableListOf<Int>()

    fun getCarriedCalories(): Int {
        return foodItems.stream()
            .mapToInt { it }
            .sum()
    }

    fun addCalories(calories: Int) {
        foodItems.add(calories)
    }

    override fun toString(): String {
        return foodItems.toString()
    }
}