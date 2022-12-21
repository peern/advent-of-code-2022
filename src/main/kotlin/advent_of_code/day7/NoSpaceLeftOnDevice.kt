package advent_of_code.day7

import advent_of_code.getReaderFromResourceFile

class NoSpaceLeftOnDevice(private val fileName: String = "day7_no_space_left_test.txt") {

    private val totalDiskSpace = 70000000
    private val updateDiskSpace = 30000000

    private val fileSystem = parseTerminalOutput()

    private fun parseTerminalOutput(): FileSystem {
        val inputLines = getReaderFromResourceFile(fileName).lineSequence().toList()
        val fileSystem = FileSystem()

        var i = 0

        while (i < inputLines.size) {
            var line = inputLines[i]
            if (isCommand(line)) { // This line is a command

                val commandAndArg = line.substring(2).split(" ")

                if (commandAndArg[0] == "cd") {
                    fileSystem.changeDirectory(commandAndArg[1])

                } else if (commandAndArg[0] == "ls") {
                    for (j in 1 until inputLines.size - i) {
                        line = inputLines[i + j]

                        if (isCommand(line)) { // if the next line is a command -> break this loop
                            i = i + j - 1
                            break
                        } else {
                            val (first, second) = line.split(" ")
                            val element = if (first == "dir") Folder(second) else File(second, first.toLong())
                            fileSystem.addToCurrentDirectory(element)
                        }
                    }
                }
            }
            i++
        }
        return fileSystem
    }

    private fun isCommand(line: String): Boolean {
        return line[0] == '$'
    }

    private fun getFoldersWithMaxSize(maxSize: Long): List<FolderProperties> {
        return fileSystem
            .getAllFolderProperties()
            .filter { it.size <= maxSize }
    }

    fun getSumOfFolderWithMaxSize(maxSize: Long): Long {
        return getFoldersWithMaxSize(maxSize)
            .sumOf { it.size }
    }

    fun getUnusedSpace(): Long {
        return totalDiskSpace - fileSystem.root.size
    }

    fun getNecessarySpaceForUpdate(): Long {
        return updateDiskSpace - getUnusedSpace()
    }

    fun getUsedSpace(): Long {
        return fileSystem.root.size
    }

    fun findDirectoryToDelete(): FolderProperties? {
        val necessarySpaceForUpdate = getNecessarySpaceForUpdate()
        return fileSystem
            .getAllFolderProperties()
            .filter { it.size >= necessarySpaceForUpdate }
            .minByOrNull { it.size }
    }
}

class FileSystem {
    private val workingDirectory = ArrayDeque<Folder>()

    val root = Folder("/")

    fun changeDirectory(arg: String) {
        when (arg) {
            ".." -> workingDirectory.removeFirst()
            "/" -> {
                workingDirectory.clear()
                workingDirectory.addFirst(root)
            }

            else -> workingDirectory.addFirst(workingDirectory
                .first()
                .children
                .filterIsInstance<Folder>()
                .first { it.name == arg })
        }
    }

    fun addToCurrentDirectory(element: FileSystemElement) {
        workingDirectory.first().createElement(element)
    }

    fun getAllFolderProperties(): List<FolderProperties> {
        return root.getSubFoldersWithSizeRecursive()
    }

}

interface FileSystemElement {
    val size: Long
    val name: String
    val isFolder: Boolean
}

class Folder(override val name: String) : FileSystemElement {
    override val isFolder: Boolean
        get() = false

    override val size: Long
        get() = children.sumOf { it.size }

    val children = mutableListOf<FileSystemElement>()

    fun createElement(element: FileSystemElement) {
        children.add(element)
    }

    override fun toString(): String {
        return "$name (dir)"
    }

    fun getSubFoldersWithSizeRecursive(): List<FolderProperties> {
        val result = mutableListOf<FolderProperties>()
        result.add(FolderProperties(this.name, this.size))

        if (children.filterIsInstance<Folder>().isNotEmpty()) {
            result.addAll(children
                .filterIsInstance<Folder>()
                .flatMap { it.getSubFoldersWithSizeRecursive() })
        }
        return result
    }
}

data class FolderProperties(val name: String, val size: Long)

class File(override val name: String, override val size: Long) : FileSystemElement {
    override val isFolder: Boolean
        get() = false

    override fun toString(): String {
        return "$name (file, size=$size)"
    }

}