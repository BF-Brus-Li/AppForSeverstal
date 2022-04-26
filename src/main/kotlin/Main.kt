import java.io.File
import java.io.PrintWriter

val tasksList = mutableListOf<String>()
var userName = ""

fun main() {
    println("Welcome to \"TasksListForSeverstal\"! ")

    println("Enter your name: ")
    userName = readln()

    readFromFile()
    showInformation()
    readFromInput()
    println("Have a good day, $userName!")
    writeToFile()
}

fun readFromInput() {
    while (true) {
        when (readln().lowercase()) {
            "\\end" -> break
            "\\help" -> showInformation()
            "\\view" -> viewTasks()
            "\\del" -> removeTasks()
            "\\change" -> changeTasks()
            "\\add" -> addTasks()
            else -> println("Unfortunately I didn't understand you, $userName")
        }
    }
}

fun addTasks() {
    println("$userName, enter the task you want to add: ")
    val task = readln()
    if (task.isBlank()) {
        println("$userName, you entered an empty value")
        return
    }
    tasksList.add(task)
    println("Task added successfully!")
}

fun removeTasks() {
    viewTasks()
    println("$userName, enter the number of the task you want to delete: ")
    val numberOfTask = try {
        readln().toInt()
    } catch (e: Exception) {
        println("Incorrect number provided!")
        return
    }
    if (numberOfTask in 1..tasksList.size) {
        tasksList.removeAt(numberOfTask - 1)
        println("Task remove successfully!")
    } else {
        println("There is no task with this number...")
        removeTasks()
    }
}

fun changeTasks() {
    viewTasks()
    println("$userName, enter the number of the task you want to change: ")
    val numberOfTask = try {
        readln().toInt()
    } catch (e: Exception) {
        println("Incorrect number provided!")
        return
    }
    if (numberOfTask !in 1..tasksList.size) {
        println("There is no task with this number...")
        changeTasks()
    }
    println("Would you like to change this task: ${tasksList[numberOfTask - 1]}?")
    when (readln().lowercase()) {
        "yes", "да", "+" -> {
            println("$userName, enter the modified task type: ")
            tasksList[numberOfTask - 1] = readln()
        }
        "no", "нет", "-" -> println("$userName: enter the command: ")
        else -> {
            println("Unfortunately I didn't understand you: $userName")
        }
    }
    println("Task change successfully!")
    viewTasks()
}

fun showInformation() {
    println(
        """
Hello, $userName!
                  
This is a simple console application that will help you save your tasks.
Also, this application can delete and change your tasks, for this you will need to register a specific command.
Command List: 1) Deleting tasks - \del
              2) Changing tasks- \change
              3) Information - \help
              4) View available tasks - \view 
              5) End the program - \end 
              6) Add a task - \add"""
    )
}

fun viewTasks() {
    for (el in 1..tasksList.size) {
        if (tasksList[el - 1].contains("\\d+ - ".toRegex())) {
            println(tasksList[el - 1])
        } else println("$el - ${tasksList[el - 1]}")
    }
}

fun readFromFile() {
    val readListFromFile = File("input")
        .readLines()
    if (readListFromFile.isNotEmpty()) {
        tasksList += readListFromFile
    } else println("Your tasks are empty...")

}

fun writeToFile() {
    val writeInList = PrintWriter("input")
    for (el in 1..tasksList.size) {
        if (tasksList[el - 1].contains("\\d+ - ".toRegex())) {
            writeInList.println(tasksList[el - 1])
        } else writeInList.println("$el - ${tasksList[el - 1]}")
    }
    writeInList.close()
}
