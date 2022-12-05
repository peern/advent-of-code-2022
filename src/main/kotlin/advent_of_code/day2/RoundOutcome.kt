package advent_of_code.day2

import java.util.NoSuchElementException

enum class RoundOutcome(val value: Int) {
    LOSE(0),
    DRAW(3),
    WIN(6);

    companion object {

        fun of(s: String): RoundOutcome {
            return when (s.uppercase()) {
                "X" -> LOSE
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw NoSuchElementException("Not possible to get RoundOutcome for $s")
            }
        }

    }
}