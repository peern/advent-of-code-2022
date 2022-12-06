import advent_of_code.day1.CaloryCounting
import advent_of_code.day2.RockPaperScissors
import advent_of_code.day3.RucksackReorganization
import advent_of_code.day4.CampCleanup

fun main(args: Array<String>) {

    day4()


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

fun day3() {
    val rucksackReorganization = RucksackReorganization("day3_rucksack.txt")
    println(rucksackReorganization.sumOfPrioritiesOfItemsAppearingTwice())
    println(rucksackReorganization.getBadgePrioritySum())
}

fun day4() {
    val campCleanup = CampCleanup("day4_camp_cleanup.txt")
    println(campCleanup.getCountOfFullyContainedPairs())
    println(campCleanup.getCountOfOverlappingPairs())
}

