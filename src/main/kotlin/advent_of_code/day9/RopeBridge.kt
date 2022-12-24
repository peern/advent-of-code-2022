package advent_of_code.day9

import advent_of_code.getReaderFromResourceFile

class RopeBridge(private val fileName: String = "day9_rope_bridge_test.txt") {

    fun findTailPositions(): Int {
        val grid = Grid()

        getReaderFromResourceFile(fileName)
            .lineSequence()
            .map { it.split(" ") }
            .forEach { grid.move(it[0], it[1].toInt()) }

        return grid.getNumberOfTailPositions()
    }
}

data class Point(val x: Int, val y: Int)

class Rope(knotNumber: Int) {
    val knots = MutableList(knotNumber) { Point(0, 0) }

    var head: Point
        get() = knots.first()
        set(value) {
            knots[0] = value
        }

    var tail: Point
        get() = knots.last()
        set(value) {
            knots[knots.size - 1] = value
        }
}

class Grid {
    private val markedTailPositions = mutableSetOf(Point(0, 0))
    private var startPos = Point(0, 0)
    var headPos = Point(0, 0)
    private var tailPos = Point(0, 0)

    fun move(direction: String, steps: Int) {
        for (i in 1..steps) {
            when (direction) {
                "R" -> moveHeadOneRight()
                "U" -> moveHeadOneUp()
                "L" -> moveHeadOneLeft()
                "D" -> moveHeadOneDown()
            }
            if (tailMoreThanOneBehind()) {
                moveTailOneTowardsHead()
            }
        }
    }

    fun getNumberOfTailPositions(): Int {
        return markedTailPositions.size
    }

    fun printTailPositions() {
        markedTailPositions.forEach { println(it) }
    }

    private fun moveHeadOneRight() {
        headPos = Point(headPos.x + 1, headPos.y)
    }

    private fun moveHeadOneLeft() {
        headPos = Point(headPos.x - 1, headPos.y)
    }

    private fun moveHeadOneUp() {
        headPos = Point(headPos.x, headPos.y + 1)
    }

    private fun moveHeadOneDown() {
        headPos = Point(headPos.x, headPos.y - 1)
    }

    private fun moveTailOneTowardsHead() {
        // no move necessary when tail too close
        if (!tailMoreThanOneBehind()) {
            return
        }

        var updatedTailPos = tailPos

        if (headPos.x == tailPos.x) {
            // head and tail are in same row
            val step = if (headPos.y > tailPos.y) 1 else -1
            updatedTailPos = Point(tailPos.x, tailPos.y + step)

        } else if (headPos.y == tailPos.y) {
            // head and tail are in same column
            val step = if (headPos.x > tailPos.x) 1 else -1
            updatedTailPos = Point(tailPos.x + step, tailPos.y)

        } else if (headPos.x > tailPos.x && headPos.y > tailPos.y) {
            // head is top right from tail
            updatedTailPos = Point(tailPos.x + 1, tailPos.y + 1)
        } else if (headPos.x > tailPos.x && headPos.y < tailPos.y) {
            // head is down right from tail
            updatedTailPos = Point(tailPos.x + 1, tailPos.y - 1)
        } else if (headPos.x < tailPos.x && headPos.y > tailPos.y) {
            // head is top left from tail
            updatedTailPos = Point(tailPos.x - 1, tailPos.y + 1)
        } else if (headPos.x < tailPos.x && headPos.y < tailPos.y) {
            // head is down left from tail
            updatedTailPos = Point(tailPos.x - 1, tailPos.y - 1)
        }

        tailPos = updatedTailPos
        markedTailPositions.add(updatedTailPos)
    }

    private fun moveTrailingOneTowardsLeading(leading: Point, trailing: Point): Point {
        // no move necessary when tail too close
        if (!trailingMoreThanOneBehind(leading, trailing)) {
            return trailing
        }

        var updatedTrailingPos = trailing

        if (leading.x == trailing.x) {
            // head and tail are in same row
            val step = if (leading.y > trailing.y) 1 else -1
            updatedTrailingPos = Point(trailing.x, trailing.y + step)

        } else if (leading.y == trailing.y) {
            // head and tail are in same column
            val step = if (leading.x > trailing.x) 1 else -1
            updatedTrailingPos = Point(trailing.x + step, trailing.y)

        } else if (leading.x > trailing.x && leading.y > trailing.y) {
            // head is top right from tail
            updatedTrailingPos = Point(trailing.x + 1, trailing.y + 1)
        } else if (leading.x > trailing.x && leading.y < trailing.y) {
            // head is down right from tail
            updatedTrailingPos = Point(trailing.x + 1, trailing.y - 1)
        } else if (leading.x < trailing.x && leading.y > trailing.y) {
            // head is top left from tail
            updatedTrailingPos = Point(trailing.x - 1, trailing.y + 1)
        } else if (leading.x < trailing.x && leading.y < trailing.y) {
            // head is down left from tail
            updatedTrailingPos = Point(trailing.x - 1, trailing.y - 1)
        }

        return updatedTrailingPos
    }

    fun trailingMoreThanOneBehind(leading: Point, trailing: Point): Boolean {
        val deltaX = leading.x - trailing.x
        val deltaY = leading.y - trailing.y

        return !((-1 <= deltaX && deltaX <= 1)
                && (-1 <= deltaY && deltaY <= 1))
    }

    fun tailMoreThanOneBehind(): Boolean {
        val deltaX = headPos.x - tailPos.x
        val deltaY = headPos.y - tailPos.y

        return !((-1 <= deltaX && deltaX <= 1)
                && (-1 <= deltaY && deltaY <= 1))
    }
}