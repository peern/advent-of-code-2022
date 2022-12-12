package advent_of_code.day6

import advent_of_code.getReaderFromResourceFile

class TuningTrouble(private val fileName: String = "day6_tuning_trouble_test.txt") {

    private val datastreams: MutableList<String> = getReaderFromResourceFile(fileName).lineSequence().toMutableList()
    
    fun getAllCountsPartOne(): List<Int> {
        return datastreams.map { countBeforeFirstMarkerDetection(it) }.toList()
    }

    fun getAllCountsPartTwo(): List<Int> {
        return datastreams.map { countBeforeFirstMarkerDetection(it, 14) }.toList()
    }

    private fun countBeforeFirstMarkerDetection(datastream: String, differentCharacters: Int = 4): Int {
        return datastream.windowed(size = differentCharacters)
            .withIndex()
            .map { IndexedValue(it.index, containsDuplicatedCharacter(it.value)) }
            .first { !it.value }
            .index + differentCharacters
    }

    private fun containsDuplicatedCharacter(text: String): Boolean {
        return text.asSequence()
            .mapIndexed { index, c -> text.indexOf(c, index + 1) > -1 }
            .any { it }
    }


}