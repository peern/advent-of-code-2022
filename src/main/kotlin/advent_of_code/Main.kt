import advent_of_code.day1.CaloryCounting
import advent_of_code.day2.RockPaperScissors
import advent_of_code.day3.RucksackReorganization
import advent_of_code.day4.CampCleanup
import advent_of_code.day5.SupplyStacks
import advent_of_code.day6.TuningTrouble
import advent_of_code.day7.NoSpaceLeftOnDevice
import advent_of_code.day8.TreetopTreeHouse
import advent_of_code.day9.RopeBridge

fun main(args: Array<String>) {
    day9()
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

fun day5() {
    val supplyStacks = SupplyStacks("day5_supply_stacks.txt")
    println(supplyStacks.performRearrangementsPart2())
    print(supplyStacks.getTopElements())
}

fun day6() {
    val tuningTrouble = TuningTrouble("day6_tuning_trouble.txt")
    println(tuningTrouble.getAllCountsPartOne())
    println(tuningTrouble.getAllCountsPartTwo())
}

fun day7() {
    val noSpaceLeftOnDevice = NoSpaceLeftOnDevice("day7_no_space_left.txt")
    println("SumOfFolderWithMaxSize 100000: ${noSpaceLeftOnDevice.getSumOfFolderWithMaxSize(100000)}")
    println("Used space: ${noSpaceLeftOnDevice.getUsedSpace()}")
    println("Unused space: ${noSpaceLeftOnDevice.getUnusedSpace()}")
    println("Necessary space for update: ${noSpaceLeftOnDevice.getNecessarySpaceForUpdate()}")
    println("Directory to delete: ${noSpaceLeftOnDevice.findDirectoryToDelete()}")
}

fun day8() {
    val treetopTreeHouse = TreetopTreeHouse("day8_tree_house.txt")
//    val treetopTreeHouse = TreetopTreeHouse()
    println(treetopTreeHouse.findHighestScenicScore())
}

fun day9() {
//    val ropeBridge = RopeBridge()
    val ropeBridge = RopeBridge("day9_rope_bridge.txt")
    println(ropeBridge.findTailPositions())
}
