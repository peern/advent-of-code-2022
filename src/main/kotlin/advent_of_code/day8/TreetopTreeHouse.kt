package advent_of_code.day8

import advent_of_code.getReaderFromResourceFile

class TreetopTreeHouse(private val fileName: String = "day8_tree_house_test.txt") {

    private val trees = mutableListOf<MutableList<Int>>()


    init {
        getReaderFromResourceFile(fileName)
            .lineSequence()
            .forEach { trees.add(it.toCharArray().map { s -> s.digitToInt() }.toMutableList()) }

        println(isVisibleFromLeft())
        println(isVisibleFromLeft(1, 3))
    }

    fun countVisibleTrees(): Int {
        var count = 0

        count += trees[0].size * 2 // top and bottom row
        count += trees.size * 2 // left and right column
        count -= 4// corners were doubled

        for (y in 1 until trees.size - 1) {
            for (x in 1 until trees[y].size - 1) {
                if (isVisibleFromOutside(x, y)) {
                    count++
                }
            }
        }

        return count
    }

    private fun isVisibleFromOutside(x: Int, y: Int): Boolean {
        return isVisibleFromLeft(x, y)
                || isVisibleFromTop(x, y)
                || isVisibleFromRight(x, y)
                || isVisibleFromBottom(x, y)
    }

    fun isVisibleFromLeft(x: Int = 1, y: Int = 1): Boolean {
        val height = trees[y][x]
        return trees[y].subList(0, x).all { it < height }
    }

    fun isVisibleFromRight(x: Int, y: Int): Boolean {
        val height = trees[y][x]
        return trees[y].subList(x + 1, trees[y].size).all { it < height }
    }

    fun isVisibleFromTop(x: Int, y: Int): Boolean {
        val height = trees[y][x]

        for (i in 0 until y) {
            if (trees[i][x] >= height) {
                return false
            }
        }

        return true
    }

    fun isVisibleFromBottom(x: Int, y: Int): Boolean {
        val height = trees[y][x]

        for (i in y + 1 until trees.size) {
            if (trees[i][x] >= height) {
                return false
            }
        }

        return true
    }

    fun findHighestScenicScore(): Int {
        return trees.flatMapIndexed { y, rows ->
            List(rows.size) { x -> getViewingDistanceScore(x, y) }
        }.max()
    }

    fun getViewingDistanceScore(x: Int, y: Int): Int {
        return (leftViewingDistance(x, y)
                * upViewingDistance(x, y)
                * rightViewingDistance(x, y)
                * downViewingDistance(x, y))
    }

    fun leftViewingDistance(x: Int, y: Int): Int {
        var distance = 0

        if (x == 0) return distance

        val height = trees[y][x]

        for (i in x - 1 downTo 0) {
            if (trees[y][i] < height) {
                distance++
            } else {
                return distance + 1
            }
        }
        return distance
    }

    fun rightViewingDistance(x: Int, y: Int): Int {
        var distance = 0
        val lastIndex = trees[y].size - 1

        if (x == lastIndex) return distance

        val height = trees[y][x]

        for (i in x + 1..lastIndex) {
            if (trees[y][i] < height) {
                distance++
            } else {
                return distance + 1
            }
        }
        return distance
    }

    fun downViewingDistance(x: Int, y: Int): Int {
        var distance = 0
        val lastIndex = trees.size - 1

        if (y == lastIndex) return distance

        val height = trees[y][x]

        for (i in y + 1..lastIndex) {
            if (trees[i][x] < height) {
                distance++
            } else {
                return distance + 1
            }
        }
        return distance
    }

    fun upViewingDistance(x: Int, y: Int): Int {
        var distance = 0

        if (y == 0) return distance

        val height = trees[y][x]

        for (i in y - 1 downTo 0) {
            if (trees[i][x] < height) {
                distance++
            } else {
                return distance + 1
            }
        }
        return distance
    }
}