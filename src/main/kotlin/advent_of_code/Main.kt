import advent_of_code.day1.CaloryCounting
import advent_of_code.day2.RockPaperScissors

fun main(args: Array<String>) {

//    day1()
    day2()
}

fun day1() {
    val caloryCounting = CaloryCounting("day1_input.txt")
    println(caloryCounting.findCaloriesOfElfCarryingMostCalories())
    println(caloryCounting.findCaloriesOfTopNElvesCarryingMostCalories(3))
}

fun day2() {
    val rockPaperScissors = RockPaperScissors()
    println(rockPaperScissors.computeTotalScore())
}

