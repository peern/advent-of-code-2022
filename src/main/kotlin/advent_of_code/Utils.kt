package advent_of_code

import java.io.BufferedReader

fun getReaderFromResourceFile(fileName: String): BufferedReader? {
    return object {}.javaClass.classLoader.getResourceAsStream(fileName)?.bufferedReader()
}