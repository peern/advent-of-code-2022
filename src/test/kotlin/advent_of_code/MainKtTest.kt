package advent_of_code

import advent_of_code.day1.CaloryCounting
import advent_of_code.day2.RockPaperScissors
import advent_of_code.day3.RucksackReorganization
import advent_of_code.day4.CampCleanup
import advent_of_code.day5.SupplyStacks
import advent_of_code.day6.TuningTrouble
import advent_of_code.day7.NoSpaceLeftOnDevice
import advent_of_code.day8.TreetopTreeHouse
import advent_of_code.day9.Grid
import advent_of_code.day9.Point
import advent_of_code.day9.RopeBridge
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
        assertEquals(7824, rucksackReorganization.sumOfPrioritiesOfItemsAppearingTwice())
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
        assertEquals(listOf(19, 23, 23, 29, 26), tuningTrouble.getAllCountsPartTwo())

        val tuningTrouble2 = TuningTrouble("day6_tuning_trouble.txt")
        assertEquals(listOf(1779), tuningTrouble2.getAllCountsPartOne())
        assertEquals(listOf(2635), tuningTrouble2.getAllCountsPartTwo())
    }

    @Test
    fun day7() {
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice("day7_no_space_left_test.txt")
        assertEquals(95437, noSpaceLeftOnDevice.getSumOfFolderWithMaxSize(100000))
        assertEquals("d", noSpaceLeftOnDevice.findDirectoryToDelete()?.name)
        assertEquals(24933642, noSpaceLeftOnDevice.findDirectoryToDelete()?.size)

        val noSpaceLeftOnDevice2 = NoSpaceLeftOnDevice("day7_no_space_left.txt")
        assertEquals(1300850, noSpaceLeftOnDevice2.findDirectoryToDelete()?.size)
    }

    @Test
    fun day8() {
        val treetopTreeHouse = TreetopTreeHouse()
        assertTrue(treetopTreeHouse.isVisibleFromLeft(1, 1))
        assertFalse(treetopTreeHouse.isVisibleFromRight(1, 1))
        assertTrue(treetopTreeHouse.isVisibleFromRight(2, 1))
        assertTrue(treetopTreeHouse.isVisibleFromRight(3, 2))
        assertTrue(treetopTreeHouse.isVisibleFromRight(1, 2))

        assertTrue(treetopTreeHouse.isVisibleFromTop(1, 1))
        assertTrue(treetopTreeHouse.isVisibleFromTop(2, 1))
        assertFalse(treetopTreeHouse.isVisibleFromTop(2, 2))
        assertFalse(treetopTreeHouse.isVisibleFromTop(3, 3))

        assertFalse(treetopTreeHouse.isVisibleFromBottom(1, 1))
        assertTrue(treetopTreeHouse.isVisibleFromBottom(2, 3))

        assertEquals(21, treetopTreeHouse.countVisibleTrees())

        assertEquals(1, treetopTreeHouse.leftViewingDistance(2, 1))
        assertEquals(2, treetopTreeHouse.leftViewingDistance(2, 3))

        assertEquals(2, treetopTreeHouse.rightViewingDistance(2, 1))
        assertEquals(2, treetopTreeHouse.rightViewingDistance(2, 3))

        assertEquals(2, treetopTreeHouse.downViewingDistance(2, 1))
        assertEquals(1, treetopTreeHouse.downViewingDistance(2, 3))

        assertEquals(1, treetopTreeHouse.upViewingDistance(2, 1))
        assertEquals(2, treetopTreeHouse.upViewingDistance(2, 3))

        assertEquals(8, treetopTreeHouse.findHighestScenicScore())

        val treetopTreeHouse2 = TreetopTreeHouse("day8_tree_house.txt")
        assertEquals(235200, treetopTreeHouse2.findHighestScenicScore())
    }

    @Test
    fun day9() {
        val ropeBridge = RopeBridge()
        assertEquals(13, ropeBridge.findTailPositions())

        val ropeBridge2 = RopeBridge("day9_rope_bridge.txt")
        assertEquals(6023, ropeBridge2.findTailPositions())
    }

    @Test
    fun day9_grid() {
        val grid = Grid()

        grid.headPos = Point(1, 1)
        assertFalse(grid.tailMoreThanOneBehind())

        grid.headPos = Point(1, 0)
        assertFalse(grid.tailMoreThanOneBehind())

        grid.headPos = Point(1, -1)
        assertFalse(grid.tailMoreThanOneBehind())

        grid.headPos = Point(0, -1)
        assertFalse(grid.tailMoreThanOneBehind())

        grid.headPos = Point(-1, -1)
        assertFalse(grid.tailMoreThanOneBehind())

        grid.headPos = Point(-1, 0)
        assertFalse(grid.tailMoreThanOneBehind())

        grid.headPos = Point(-1, 1)
        assertFalse(grid.tailMoreThanOneBehind())

        grid.headPos = Point(0, -1)
        assertFalse(grid.tailMoreThanOneBehind())


        // assertTrue
        grid.headPos = Point(2, 2)
        assertTrue(grid.tailMoreThanOneBehind())

        grid.headPos = Point(2, 0)
        assertTrue(grid.tailMoreThanOneBehind())

        grid.headPos = Point(2, -1)
        assertTrue(grid.tailMoreThanOneBehind())

        grid.headPos = Point(2, -2)
        assertTrue(grid.tailMoreThanOneBehind())
    }

}