package advent_of_code

import advent_of_code.day1.CaloryCounting
import advent_of_code.day2.RockPaperScissors
import advent_of_code.day3.RucksackReorganization
import advent_of_code.day4.CampCleanup
import advent_of_code.day5.SupplyStacks
import advent_of_code.day6.TuningTrouble
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
    
    @Test
    fun day5() {
        val supplyStacks = SupplyStacks("day5_supply_stacks.txt")
        supplyStacks.performRearrangementsPart1()
        assertEquals("CWMTGHBDW", supplyStacks.getTopElements())
    }
    
    @Test
    fun day6() {
        val tuningTrouble = TuningTrouble("day6_tuning_trouble_test.txt")
        assertEquals(listOf(7, 5, 6, 10, 11), tuningTrouble.getAllCountsPartOne())
        assertEquals(listOf(19, 23, 23, 29,26), tuningTrouble.getAllCountsPartTwo())

        val tuningTrouble2 = TuningTrouble("day6_tuning_trouble.txt")
        assertEquals(listOf(1779), tuningTrouble2.getAllCountsPartOne())
        assertEquals(listOf(2635), tuningTrouble2.getAllCountsPartTwo())
    }

}