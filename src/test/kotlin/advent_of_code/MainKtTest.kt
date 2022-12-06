package advent_of_code

import advent_of_code.day1.CaloryCounting
import advent_of_code.day2.RockPaperScissors
import advent_of_code.day3.RucksackReorganization
import advent_of_code.day4.CampCleanup
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun day1() {
        val caloryCounting = CaloryCounting("day1_input.txt")
        assertEquals(69310, caloryCounting.findCaloriesOfElfCarryingMostCalories())
        assertEquals(206104, caloryCounting.findCaloriesOfTopNElvesCarryingMostCalories(3))
    }

    @Test
    fun day2() {
        val rockPaperScissors = RockPaperScissors("day2_strategy_guide.txt")
        assertEquals(9975, rockPaperScissors.computeTotalScore())
    }

    @Test
    fun day3() {
        val rucksackReorganization = RucksackReorganization("day3_rucksack.txt")
        assertEquals(7824 , rucksackReorganization.sumOfPrioritiesOfItemsAppearingTwice())
        assertEquals(2798, rucksackReorganization.getBadgePrioritySum())

    }

    @Test
    fun day4() {
        val campCleanup = CampCleanup("day4_camp_cleanup.txt")
        assertEquals(466, campCleanup.getCountOfFullyContainedPairs())
        assertEquals(865, campCleanup.getCountOfOverlappingPairs())
    }

}