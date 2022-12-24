package advent_of_code.day9

import advent_of_code.getReaderFromResourceFile

class RopeBridge(private val fileName: String = "day9_rope_bridge_test.txt") {

    fun findTailPositions1(): Int {
        val grid = Grid(Rope(2))

        getReaderFromResourceFile(fileName)
            .lineSequence()
            .map { it.split(" ") }
            .forEach { grid.move(it[0], it[1].toInt()) }

        return grid.getNumberOfTailPositions()
    }

    fun findTailPositions2(): Int {
        val grid = Grid(Rope(10))

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

class Grid(private val rope: Rope = Rope(10)) {
    private val markedTailPositions = mutableSetOf(Point(0, 0))

    var headPos: Point
        get() = rope.head
        set(value) {
            rope.head = value
        }

    private var tailPos: Point
        get() = rope.tail
        set(value) {
            rope.tail = value
        }


    fun move(direction: String, steps: Int) {
        for (i in 1..steps) {
            when (direction) {
                "R" -> moveHeadOneRight()
                "U" -> moveHeadOneUp()
                "L" -> moveHeadOneLeft()
                "D" -> moveHeadOneDown()
            }

            for (j in 0 until rope.knots.size) {
                if (j + 1 >= rope.knots.size) {
                    // check if this is the tail element and if yes add to marked positions
                    markedTailPositions.add(rope.tail)
                    break
                }

                val trailing = rope.knots[j + 1]
                val leading = rope.knots[j]
                if (trailingMoreThanOneBehind(trailing, leading)) {
                    val updatedTrailingPos = moveTrailingOneTowardsLeading(trailing, leading)
                    rope.knots[j + 1] = updatedTrailingPos
                }
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
        tailPos = moveTrailingOneTowardsLeading(tailPos, headPos)
        markedTailPositions.add(tailPos)
    }

    private fun moveTrailingOneTowardsLeading(trailing: Point, leading: Point): Point {
        // no move necessary when tail too close
        if (!trailingMoreThanOneBehind(trailing, leading)) {
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

    private fun trailingMoreThanOneBehind(trailing: Point, leading: Point): Boolean {
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