package advent_of_code.day2

import java.util.NoSuchElementException

enum class HandShape(val value: Int) {
    ROCK(1) {
        override val superiorType get() = PAPER
        override val inferiorType get() = SCISSORS
    },
    PAPER(2) {
        override val superiorType get() = SCISSORS
        override val inferiorType get() = ROCK
    },
    SCISSORS(3) {
        override val superiorType get() = ROCK
        override val inferiorType get() = PAPER
    };

    abstract val superiorType: HandShape
    abstract val inferiorType: HandShape

    companion object {

        fun of(s: String): HandShape {
            return when (s.uppercase()) {
                "A" -> ROCK
                "B" -> PAPER
                "C" -> SCISSORS
                else -> throw NoSuchElementException("Not possible to get HandShape for $s")
            }
        }

    }

    fun playAgainst(yourShape: HandShape?): Int {
        return when (yourShape) {
            superiorType -> 6
            inferiorType -> 0
            else -> 3
        }
    }

}