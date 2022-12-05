package advent_of_code.day2

import advent_of_code.getReaderFromResourceFile
import kotlin.streams.asSequence

class RockPaperScissors(fileName: String = "day2_strategy_guide.txt") {

    private val plays: List<PlayRound>

    init {
        val reader = getReaderFromResourceFile(fileName) ?: throw IllegalArgumentException("No file could be loaded")
        this.plays = reader.lines().asSequence()
            .map { it.split(" ") }
            .map { PlayRound(HandShape.of(it[0]), RoundOutcome.of(it[1])) }
            .toList()
    }

    fun computeTotalScore(): Int {
        return plays.asSequence()
            .map { getYourShape(it).value + it.targetResult.value }
            .sum()
    }

    private fun getYourShape(playRound: PlayRound): HandShape {
        return when (playRound.targetResult) {
            RoundOutcome.LOSE -> playRound.opponentsShape.inferiorType
            RoundOutcome.DRAW -> playRound.opponentsShape
            RoundOutcome.WIN -> playRound.opponentsShape.superiorType
        }
    }

}

