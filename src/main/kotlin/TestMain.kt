import java.io.File

private fun main() {
    val readListFromFile = File("input")
        .readLines()
    println("\t${readListFromFile.joinToString(" \n\t")}")
    tasksList += readListFromFile
    println(tasksList.joinToString(" "))
}